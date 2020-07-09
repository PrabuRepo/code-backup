package com.practice.ood.problems;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.Random;

public class JigSaw {

	public static Edge createRandomEdge(String code) {
		Random random = new Random();
		Shape type = Shape.INNER;
		if (random.nextBoolean()) {
			type = Shape.OUTER;
		}
		return new Edge(type, code);
	}

	public static Edge[] createEdges(Piece[][] puzzle, int column, int row) {
		String key = column + ":" + row + ":";
		/* Get left edge */
		Edge left = column == 0 ? new Edge(Shape.FLAT, key + "h|e")
				: puzzle[row][column - 1].getEdgeWithOrientation(Orientation.RIGHT)._createMatchingEdge();

		/* Get top edge */
		Edge top = row == 0 ? new Edge(Shape.FLAT, key + "v|e")
				: puzzle[row - 1][column].getEdgeWithOrientation(Orientation.BOTTOM)._createMatchingEdge();

		/* Get right edge */
		Edge right = column == puzzle[row].length - 1 ? new Edge(Shape.FLAT, key + "h|e") : createRandomEdge(key + "h");

		/* Get bottom edge */
		Edge bottom = row == puzzle.length - 1 ? new Edge(Shape.FLAT, key + "v|e") : createRandomEdge(key + "v");

		Edge[] edges = { left, top, right, bottom };
		return edges;
	}

	public static LinkedList<Piece> initializePuzzle(int size) {
		/* Create completed puzzle. */
		Piece[][] puzzle = new Piece[size][size];
		for (int row = 0; row < size; row++) {
			for (int column = 0; column < size; column++) {
				Edge[] edges = createEdges(puzzle, column, row);
				puzzle[row][column] = new Piece(edges);
			}
		}

		/* Shuffle and rotate pieces. */
		LinkedList<Piece> pieces = new LinkedList<Piece>();
		Random r = new Random();
		for (int row = 0; row < size; row++) {
			for (int column = 0; column < size; column++) {
				int rotations = r.nextInt(4);
				Piece piece = puzzle[row][column];
				piece.rotateEdgesBy(rotations);
				int index = pieces.size() == 0 ? 0 : r.nextInt(pieces.size());
				pieces.add(index, piece);
			}
		}

		return pieces;
	}

	public static String solutionToString(Piece[][] solution) {
		StringBuilder sb = new StringBuilder();
		for (int h = 0; h < solution.length; h++) {
			for (int w = 0; w < solution[h].length; w++) {
				Piece p = solution[h][w];
				if (p == null) {
					sb.append("null");
				} else {
					sb.append(p.toString());
				}
			}
			sb.append("\n");
		}
		return sb.toString();
	}

	/* Used for testing. Check if puzzle is solved. */
	public static boolean validate(Piece[][] solution) {
		if (solution == null)
			return false;
		for (int r = 0; r < solution.length; r++) {
			for (int c = 0; c < solution[r].length; c++) {
				Piece piece = solution[r][c];
				if (piece == null)
					return false;
				if (c > 0) { /* match left */
					Piece left = solution[r][c - 1];
					if (!left.getEdgeWithOrientation(Orientation.RIGHT)
							.fitsWith(piece.getEdgeWithOrientation(Orientation.LEFT))) {
						return false;
					}
				}
				if (c < solution[r].length - 1) { /* match right */
					Piece right = solution[r][c + 1];
					if (!right.getEdgeWithOrientation(Orientation.LEFT)
							.fitsWith(piece.getEdgeWithOrientation(Orientation.RIGHT))) {
						return false;
					}
				}
				if (r > 0) { /* match top */
					Piece top = solution[r - 1][c];
					if (!top.getEdgeWithOrientation(Orientation.BOTTOM).fitsWith(piece.getEdgeWithOrientation(Orientation.TOP))) {
						return false;
					}
				}
				if (r < solution.length - 1) { /* match bottom */
					Piece bottom = solution[r + 1][c];
					if (!bottom.getEdgeWithOrientation(Orientation.TOP)
							.fitsWith(piece.getEdgeWithOrientation(Orientation.BOTTOM))) {
						return false;
					}
				}
			}
		}
		return true;
	}

	public static boolean testSize(int size) {
		LinkedList<Piece> pieces = initializePuzzle(size);
		Puzzle puzzle = new Puzzle(size, pieces);
		puzzle.solve();
		Piece[][] solution = puzzle.getCurrentSolution();
		System.out.println(solutionToString(solution));
		boolean result = validate(solution);
		System.out.println(result);
		return result;
	}

	public static void main(String[] args) {
		for (int size = 1; size < 10; size++) {
			if (!testSize(size)) {
				System.out.println("ERROR: " + size);
			}
		}

	}

}

class Edge {
	private Shape shape;
	private String code; // used to mock how pieces would fit together.
	private Piece parentPiece;

	public Edge(Shape shape, String code) {
		this.shape = shape;
		this.code = code;
	}

	private String getCode() {
		return code;
	}

	public Edge _createMatchingEdge() {
		if (shape == Shape.FLAT)
			return null;
		return new Edge(shape.getOpposite(), getCode());
	}

	/* Check if this edge fits into the other one. */
	public boolean fitsWith(Edge edge) {
		return edge.getCode().equals(getCode());
	}

	/* Set parent piece. */
	public void setParentPiece(Piece parentPiece) {
		this.parentPiece = parentPiece;
	}

	/* Get the parent piece. */
	public Piece getParentPiece() {
		return parentPiece;
	}

	/* Return the shape of the edge. */
	public Shape getShape() {
		return shape;
	}

	public String toString() {
		return code;
	}
}

enum Orientation {
	LEFT, TOP, RIGHT, BOTTOM; // Should stay in this order

	public Orientation getOpposite() {
		switch (this) {
		case LEFT:
			return RIGHT;
		case RIGHT:
			return LEFT;
		case TOP:
			return BOTTOM;
		case BOTTOM:
			return TOP;
		default:
			return null;
		}
	}
}

enum Shape {
	INNER, OUTER, FLAT;

	public Shape getOpposite() {
		switch (this) {
		case INNER:
			return OUTER;
		case OUTER:
			return INNER;
		default:
			return null;
		}
	}
}

class Piece {
	private final static int NUMBER_OF_EDGES = 4;
	private HashMap<Orientation, Edge> edges = new HashMap<Orientation, Edge>();

	public Piece(Edge[] edgeList) {
		Orientation[] orientations = Orientation.values();
		for (int i = 0; i < edgeList.length; i++) {
			Edge edge = edgeList[i];
			edge.setParentPiece(this);
			edges.put(orientations[i], edge);
		}
	}

	/* Set this edge in the appropriate orientation, rotating the piece as necessary. */
	public void setEdgeAsOrientation(Edge edge, Orientation orientation) {
		Orientation currentOrientation = getOrientation(edge);
		rotateEdgesBy(orientation.ordinal() - currentOrientation.ordinal());
	}

	/* Return the current orientation of the edge. */
	private Orientation getOrientation(Edge edge) {
		for (Entry<Orientation, Edge> entry : edges.entrySet()) {
			if (entry.getValue() == edge) {
				return entry.getKey();
			}
		}
		return null;
	}

	/* Rotate edges by "numberRotations". */
	public void rotateEdgesBy(int numberRotations) {
		Orientation[] orientations = Orientation.values();
		HashMap<Orientation, Edge> rotated = new HashMap<Orientation, Edge>();

		numberRotations = numberRotations % NUMBER_OF_EDGES;
		if (numberRotations < 0)
			numberRotations += NUMBER_OF_EDGES;

		for (int i = 0; i < orientations.length; i++) {
			Orientation oldOrientation = orientations[(i - numberRotations + NUMBER_OF_EDGES) % NUMBER_OF_EDGES];
			Orientation newOrientation = orientations[i];
			rotated.put(newOrientation, edges.get(oldOrientation));
		}
		edges = rotated;
	}

	/* Check if this piece is a corner piece. */
	public boolean isCorner() {
		Orientation[] orientations = Orientation.values();
		for (int i = 0; i < orientations.length; i++) {
			Shape current = edges.get(orientations[i]).getShape();
			Shape next = edges.get(orientations[(i + 1) % NUMBER_OF_EDGES]).getShape();
			if (current == Shape.FLAT && next == Shape.FLAT) {
				return true;
			}
		}
		return false;
	}

	/* Check if this piece has a border edge. */
	public boolean isBorder() {
		Orientation[] orientations = Orientation.values();
		for (int i = 0; i < orientations.length; i++) {
			if (edges.get(orientations[i]).getShape() == Shape.FLAT) {
				return true;
			}
		}
		return false;
	}

	/* Get edge at this orientation. */
	public Edge getEdgeWithOrientation(Orientation orientation) {
		return edges.get(orientation);
	}

	/* Return the edge that matches targetEdge. Returns null if there is no match. */
	public Edge getMatchingEdge(Edge targetEdge) {
		for (Edge e : edges.values()) {
			if (targetEdge.fitsWith(e)) {
				return e;
			}
		}
		return null;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		Orientation[] orientations = Orientation.values();
		for (Orientation o : orientations) {
			sb.append(edges.get(o).toString() + ",");
		}
		return "[" + sb.toString() + "]";
	}
}

class Puzzle {
	private LinkedList<Piece> pieces; /* Remaining pieces left to put away. */
	private Piece[][] solution;
	private int size;

	public Puzzle(int size, LinkedList<Piece> pieces) {
		this.pieces = pieces;
		this.size = size;
	}

	/* Group pieces into border pieces (including corners) and inside pieces. */
	public void groupPieces(LinkedList<Piece> cornerPieces, LinkedList<Piece> borderPieces,
			LinkedList<Piece> insidePieces) {
		for (Piece p : pieces) {
			if (p.isCorner()) {
				cornerPieces.add(p);
			} else if (p.isBorder()) {
				borderPieces.add(p);
			} else {
				insidePieces.add(p);
			}
		}
	}

	/* Orient this corner piece so that its flat edges are on the top and left. */
	public void orientTopLeftCorner(Piece piece) {
		if (!piece.isCorner())
			return;

		Orientation[] orientations = Orientation.values();
		for (int i = 0; i < orientations.length; i++) {
			Edge current = piece.getEdgeWithOrientation(orientations[i]);
			Edge next = piece.getEdgeWithOrientation(orientations[(i + 1) % orientations.length]);
			if (current.getShape() == Shape.FLAT && next.getShape() == Shape.FLAT) {
				piece.setEdgeAsOrientation(current, Orientation.LEFT);
				return;
			}
		}
	}

	/* Bounds check. Check if this index is on a border (0 or size - 1) */
	public boolean isBorderIndex(int location) {
		return location == 0 || location == size - 1;
	}

	/* Given a list of pieces, check if any have an edge that matches this piece. Return the edge*/
	private Edge getMatchingEdge(Edge targetEdge, LinkedList<Piece> pieces) {
		for (Piece piece : pieces) {
			Edge matchingEdge = piece.getMatchingEdge(targetEdge);
			if (matchingEdge != null) {
				return matchingEdge;
			}
		}
		return null;
	}

	/* Put the edge/piece into the solution, turn it appropriately, and remove from list. */
	private void setEdgeInSolution(LinkedList<Piece> pieces, Edge edge, int row, int column, Orientation orientation) {
		Piece piece = edge.getParentPiece();
		piece.setEdgeAsOrientation(edge, orientation);
		pieces.remove(piece);
		solution[row][column] = piece;
	}

	/* Return the list where a piece with this index would be found. */
	private LinkedList<Piece> getPieceListToSearch(LinkedList<Piece> cornerPieces, LinkedList<Piece> borderPieces,
			LinkedList<Piece> insidePieces, int row, int column) {
		if (isBorderIndex(row) && isBorderIndex(column)) {
			return cornerPieces;
		} else if (isBorderIndex(row) || isBorderIndex(column)) {
			return borderPieces;
		} else {
			return insidePieces;
		}
	}

	/* Find the matching piece within piecesToSearch and insert it at row, column. */
	private boolean fitNextEdge(LinkedList<Piece> piecesToSearch, int row, int column) {
		if (row == 0 && column == 0) {
			Piece p = piecesToSearch.remove();
			orientTopLeftCorner(p);
			solution[0][0] = p;
		} else {
			/* Get the right edge and list to match. */
			Piece pieceToMatch = column == 0 ? solution[row - 1][0] : solution[row][column - 1];
			Orientation orientationToMatch = column == 0 ? Orientation.BOTTOM : Orientation.RIGHT;
			Edge edgeToMatch = pieceToMatch.getEdgeWithOrientation(orientationToMatch);

			/* Get matching edge. */
			Edge edge = getMatchingEdge(edgeToMatch, piecesToSearch);
			if (edge == null)
				return false; // Can't solve

			Orientation orientation = orientationToMatch.getOpposite();
			setEdgeInSolution(piecesToSearch, edge, row, column, orientation);
		}
		return true;
	}

	public boolean solve() {
		/* Group pieces. */
		LinkedList<Piece> cornerPieces = new LinkedList<Piece>();
		LinkedList<Piece> borderPieces = new LinkedList<Piece>();
		LinkedList<Piece> insidePieces = new LinkedList<Piece>();
		groupPieces(cornerPieces, borderPieces, insidePieces);

		/* Walk through puzzle, finding the piece that joins the previous one. */
		solution = new Piece[size][size];
		for (int row = 0; row < size; row++) {
			for (int column = 0; column < size; column++) {
				LinkedList<Piece> piecesToSearch = getPieceListToSearch(cornerPieces, borderPieces, insidePieces, row, column);
				if (!fitNextEdge(piecesToSearch, row, column)) {
					return false;
				}
			}
		}

		return true;
	}

	public Piece[][] getCurrentSolution() {
		return solution;
	}
}
