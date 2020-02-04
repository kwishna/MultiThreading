package InterviewPractice;

import java.util.*;

/**
 * Queue, DeQueue
 */
public class Collectn {

	public static void main(String[] args) {
		Stack<String> stack = new Stack<String>();
		stack.push("Krishna1");
		stack.push("Krishna2");
		stack.push("Krishna3");
		stack.push("Krishna4");
		stack.push("Krishna5");

		System.out.println(stack);
		System.out.println(stack.peek());
		System.out.println(stack.pop());
		System.out.println(stack);
		System.out.println(stack.remove("Krishna1")); // Returns true/false. Remove Matching Element.
		System.out.println(stack.search("Krishna3")); // Index Of element


		ArrayList<String> list1 = new ArrayList<String>(stack){{
			add("Hi1");
			add("Hi2");
			add("Hi3");
			add("Hi4");
		}};


		LinkedList<String> list2 = new LinkedList<>(list1);
		System.out.println("list2 getFirst - "+list2.getFirst());
		System.out.println("list2 pop - "+list2.pop());

		Deque<String> qu = new ArrayDeque<>(list2);
		System.out.println(qu);
		System.out.println(qu.poll());

		Queue queue = new PriorityQueue();
		Deque deque = new ArrayDeque<>();

	}
}
