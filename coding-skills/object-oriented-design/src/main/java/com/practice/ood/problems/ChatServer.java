package com.practice.ood.problems;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class ChatServer {

}

class AddRequest {
	private User3 fromUser;
	private User3 toUser;
	private Date date;
	RequestStatus status;

	public AddRequest(User3 from, User3 to, Date date) {
		fromUser = from;
		toUser = to;
		this.date = date;
		status = RequestStatus.Unread;
	}

	public RequestStatus getStatus() {
		return status;
	}

	public User3 getFromUser() {
		return fromUser;
	}

	public User3 getToUser() {
		return toUser;
	}

	public Date getDate() {
		return date;
	}
}

abstract class Conversation {
	protected ArrayList<User3> participants = new ArrayList<User3>();
	protected int id;
	protected ArrayList<Message> messages = new ArrayList<Message>();

	public ArrayList<Message> getMessages() {
		return messages;
	}

	public boolean addMessage(Message m) {
		messages.add(m);
		return true;
	}

	public int getId() {
		return id;
	}
}

class GroupChat extends Conversation {
	public void removeParticipant(User3 user) {
		participants.remove(user);
	}

	public void addParticipant(User3 user) {
		participants.add(user);
	}
}

class Message {
	private String content;
	private Date date;

	public Message(String content, Date date) {
		this.content = content;
		this.date = date;
	}

	public String getContent() {
		return content;
	}

	public Date getDate() {
		return date;
	}
}

class PrivateChat extends Conversation {
	public PrivateChat(User3 user1, User3 user2) {
		participants.add(user1);
		participants.add(user2);
	}

	public User3 getOtherParticipant(User3 primary) {
		if (participants.get(0) == primary) {
			return participants.get(1);
		} else if (participants.get(1) == primary) {
			return participants.get(0);
		}
		return null;
	}
}

enum RequestStatus {
	Unread, Read, Accepted, Rejected
}

class User3 {
	private int id;
	private UserStatus status = null;
	private HashMap<Integer, PrivateChat> privateChats = new HashMap<Integer, PrivateChat>();
	private ArrayList<GroupChat> groupChats = new ArrayList<GroupChat>();
	private HashMap<Integer, AddRequest> receivedAddRequests = new HashMap<Integer, AddRequest>();
	private HashMap<Integer, AddRequest> sentAddRequests = new HashMap<Integer, AddRequest>();

	private HashMap<Integer, User3> contacts = new HashMap<Integer, User3>();
	private String accountName;
	private String fullName;

	public User3(int id, String accountName, String fullName) {
		this.accountName = accountName;
		this.fullName = fullName;
		this.id = id;
	}

	public boolean sendMessageToUser(User3 toUser, String content) {
		PrivateChat chat = privateChats.get(toUser.getId());
		if (chat == null) {
			chat = new PrivateChat(this, toUser);
			privateChats.put(toUser.getId(), chat);
		}
		Message message = new Message(content, new Date());
		return chat.addMessage(message);
	}

	public boolean sendMessageToGroupChat(int groupId, String content) {
		GroupChat chat = groupChats.get(groupId);
		if (chat != null) {
			Message message = new Message(content, new Date());
			return chat.addMessage(message);
		}
		return false;
	}

	public void setStatus(UserStatus status) {
		this.status = status;
	}

	public UserStatus getStatus() {
		return status;
	}

	public boolean addContact(User3 user) {
		if (contacts.containsKey(user.getId())) {
			return false;
		} else {
			contacts.put(user.getId(), user);
			return true;
		}
	}

	public void receivedAddRequest(AddRequest req) {
		int senderId = req.getFromUser().getId();
		if (!receivedAddRequests.containsKey(senderId)) {
			receivedAddRequests.put(senderId, req);
		}
	}

	public void sentAddRequest(AddRequest req) {
		int receiverId = req.getFromUser().getId();
		if (!sentAddRequests.containsKey(receiverId)) {
			sentAddRequests.put(receiverId, req);
		}
	}

	public void removeAddRequest(AddRequest req) {
		if (req.getToUser() == this) {
			receivedAddRequests.remove(req);
		} else if (req.getFromUser() == this) {
			sentAddRequests.remove(req);
		}
	}

	public void requestAddUser(String accountName) {
		UserManager2.getInstance().addUser(this, accountName);
	}

	public void addConversation(PrivateChat conversation) {
		User3 otherUser = conversation.getOtherParticipant(this);
		privateChats.put(otherUser.getId(), conversation);
	}

	public void addConversation(GroupChat conversation) {
		groupChats.add(conversation);
	}

	public int getId() {
		return id;
	}

	public String getAccountName() {
		return accountName;
	}

	public String getFullName() {
		return fullName;
	}
}

/* UserManager2 serves as the central place for the core user actions. */
class UserManager2 {
	private static UserManager2 instance;
	private HashMap<Integer, User3> usersById = new HashMap<Integer, User3>();
	private HashMap<String, User3> usersByAccountName = new HashMap<String, User3>();
	private HashMap<Integer, User3> onlineUsers = new HashMap<Integer, User3>();

	public static UserManager2 getInstance() {
		if (instance == null) {
			instance = new UserManager2();
		}
		return instance;
	}

	public void addUser(User3 fromUser, String toAccountName) {
		User3 toUser = usersByAccountName.get(toAccountName);
		AddRequest req = new AddRequest(fromUser, toUser, new Date());
		toUser.receivedAddRequest(req);
		fromUser.sentAddRequest(req);
	}

	public void approveAddRequest(AddRequest req) {
		req.status = RequestStatus.Accepted;
		User3 from = req.getFromUser();
		User3 to = req.getToUser();
		from.addContact(to);
		to.addContact(from);
	}

	public void rejectAddRequest(AddRequest req) {
		req.status = RequestStatus.Rejected;
		User3 from = req.getFromUser();
		User3 to = req.getToUser();
		from.removeAddRequest(req);
		to.removeAddRequest(req);
	}

	public void userSignedOn(String accountName) {
		User3 user = usersByAccountName.get(accountName);
		if (user != null) {
			user.setStatus(new UserStatus(UserStatusType.Available, ""));
			onlineUsers.put(user.getId(), user);
		}
	}

	public void userSignedOff(String accountName) {
		User3 user = usersByAccountName.get(accountName);
		if (user != null) {
			user.setStatus(new UserStatus(UserStatusType.Offline, ""));
			onlineUsers.remove(user.getId());
		}
	}
}

class UserStatus {
	private String message;
	private UserStatusType type;

	public UserStatus(UserStatusType type, String message) {
		this.type = type;
		this.message = message;
	}

	public UserStatusType getStatusType() {
		return type;
	}

	public String getMessage() {
		return message;
	}
}

enum UserStatusType {
	Offline, Away, Idle, Available, Busy
}