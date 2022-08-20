import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Deque;

public class StackAndQueue {
    public static void main(String[] args) {
        DequeByThreeStacks d = new StackAndQueue().new DequeByThreeStacks();
        d.offerLast(227);
        d.offerFirst(32);
        d.pollFirst();
        d.peekFirst();
    }

    /**
     * Sort With 2 Stacks
     * <p>
     * Given an array that is initially stored in one stack, sort it with one
     * additional stacks (total 2 stacks). After sorting the original stack should
     * contain the sorted integers and from top to bottom the integers are sorted in
     * ascending order.
     */
    public void sort(LinkedList<Integer> s1) {
        if (s1.isEmpty()) {
            return;
        }
        LinkedList<Integer> s2 = new LinkedList<>();
        int prevMin = Integer.MIN_VALUE;
        int count = 0;

        while (s1.peekLast() > prevMin) {
            int min = Integer.MAX_VALUE;
            while (!s1.isEmpty() && s1.peekLast() > prevMin) {
                int val = s1.pollLast();
                if (val < min) {
                    min = val;
                    count = 1;
                } else if (val == min) {
                    count++;
                }
                s2.offerLast(val);
            }
            while (count-- >= 0) {
                s1.offerLast(min);
            }
            while (!s2.isEmpty()) {
                int val = s2.pollLast();
                if (val != min) {
                    s1.offerLast(val);
                }
            }
            prevMin = min;
        }
    }

    /**
     * Queue By Two Stacks Java: Implement a queue by using two stacks
     * <p>
     * The queue should provide size(), isEmpty(), offer(), poll() and peek()
     * operations. When the queue is empty, poll() and peek() should return null.
     */
    public class QueueByTwoStacks {
        private LinkedList<Integer> in;
        private LinkedList<Integer> out;

        public QueueByTwoStacks() {
            in = new LinkedList<Integer>();
            out = new LinkedList<Integer>();
        }

        public Integer poll() {
            if (isEmpty()) {
                return null;
            }
            shuffle();
            return out.pollLast();
        }

        public void offer(int element) {
            in.offerLast(element);
        }

        public Integer peek() {
            shuffle();
            return out.peekLast();
        }

        public int size() {
            return in.size() + out.size();
        }

        public boolean isEmpty() {
            return in.isEmpty() && out.isEmpty();
        }

        private void shuffle() {
            if (out.isEmpty()) {
                while (!in.isEmpty()) {
                    out.offerLast(in.pollLast());
                }
            }
        }
    }

    /**
     * Stack With min()
     * <p>
     * Enhance the stack implementation to support min() operation. min() should
     * return the current minimum value in the stack. If the stack is empty, min()
     * should return -1.
     */
    public class StackWithMin {
        private LinkedList<Integer> stack;
        private LinkedList<Integer> minStack;

        public StackWithMin() {
            stack = new LinkedList<>();
            minStack = new LinkedList<>();
        }

        public int pop() {
            if (stack.isEmpty()) {
                return -1;
            }
            minStack.pollLast();
            return stack.pollLast();
        }

        public void push(int element) {
            stack.offerLast(element);
            if (minStack.isEmpty()) {
                minStack.offerLast(element);
                return;
            }
            int curMin = minStack.peekLast();
            minStack.offerLast(Math.min(curMin, element));
        }

        public int top() {
            if (stack.isEmpty()) {
                return -1;
            }
            return stack.peekLast();
        }

        public int min() {
            if (stack.isEmpty()) {
                return -1;
            }
            return minStack.peekLast();
        }
    }

    /**
     * Stack by Queue(s)
     * <p>
     * Implement a stack containing integers using queue(s). The stack should
     * provide push(x), pop(), top() and isEmpty() operations.
     */
    public class StackByQueue {
        private Queue<Integer> queue;

        public StackByQueue() {
            queue = new ArrayDeque<>();
        }

        public void push(int x) {
            queue.offer(x);
        }

        public Integer pop() {
            if (queue.isEmpty()) {
                return null;
            }

            int size = queue.size();
            while (--size > 0) {
                queue.offer(queue.poll());
            }
            return queue.poll();
        }

        public Integer top() {
            if (queue.isEmpty()) {
                return null;
            }

            int val = pop();
            push(val);
            return val;
        }

        public boolean isEmpty() {
            return queue.isEmpty();
        }
    }

    /**
     * Deque By Three Stacks
     * <p>
     * Java: Implement a deque by using three stacks. The queue should provide
     * size(), isEmpty(), offerFirst(), offerLast(), pollFirst(), pollLast(),
     * peekFirst() and peekLast() operations. When the queue is empty, pollFirst(),
     * pollLast(), peekFirst() and peek() should return null.
     */
    public class DequeByThreeStacks {
        private Deque<Integer> stack1;
        private Deque<Integer> stack2;
        private Deque<Integer> stack3;

        private void shuffle() {
            if (!stack1.isEmpty() && !stack2.isEmpty()) {
                return;
            }
            if (size() == 0) {
                return;
            }

            if (stack1.isEmpty()) {
                int size = size() / 2;
                while (size-- > 0) {
                    stack3.offerLast(stack2.pollLast());
                } 
                while (!stack2.isEmpty()) {
                    stack1.offerLast(stack2.pollLast());
                }
                while (!stack3.isEmpty()) {
                    stack2.offerLast(stack3.pollLast());
                }
                return;
            }
            if (stack2.isEmpty()) {
                int size = size() / 2;
                while (size-- > 0) {
                    stack3.offerLast(stack1.pollLast());
                } 
                while (!stack1.isEmpty()) {
                    stack2.offerLast(stack1.pollLast());
                }
                while (!stack3.isEmpty()) {
                    stack1.offerLast(stack3.pollLast());
                }
            }
        }

        public DequeByThreeStacks() {
            stack1 = new ArrayDeque<>();
            stack2 = new ArrayDeque<>();
            stack3 = new ArrayDeque<>();
        }

        public void offerFirst(int element) {
            stack1.offerLast(element);
        }

        public void offerLast(int element) {
            stack2.offerLast(element);
        }

        public Integer pollFirst() {
            if (isEmpty()) {
                return null;
            }
            if (stack1.isEmpty()) {
                shuffle();
            }
            return stack1.pollLast();
        }

        public Integer pollLast() {
            if (isEmpty()) {
                return null;
            }
            if (stack2.isEmpty()) {
                shuffle();
            }
            return stack2.pollLast();
        }

        public Integer peekFirst() {
            Integer result = pollFirst();
            if (result == null) {
                return null;
            }
            offerFirst(result);
            return result;
        }

        public Integer peekLast() {
            Integer result = pollLast();
            if (result == null) {
                return null;
            }
            offerLast(result);
            return result;
        }

        public int size() {
            return stack1.size() + stack2.size();
        }

        public boolean isEmpty() {
            return size() == 0;
        }
    }
}
