package com.web.hackersrank.datastructures;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SparseArrays {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		List<String> strings = IntStream.range(0, in.nextInt()).mapToObj(i -> in.next()).collect(Collectors.toList());
		IntStream.range(0, in.nextInt()).mapToObj(i -> in.next()).
		mapToLong(q -> strings.stream().filter(q::equals).count()).forEach(System.out::println);
		
		//Get the input
		//int n = in.nextInt();
		//Stream<String> strings = IntStream.range(0, n).mapToObj(i->in.next());
		//Stream<String> queries = IntStream.range(0, n).mapToObj(i->in.next());
		//queries.mapToLong(q->strings.filter(q::equals).count()).forEach(System.out::println);
	}

}
