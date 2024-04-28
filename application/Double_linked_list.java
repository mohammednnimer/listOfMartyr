package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

public class Double_linked_list<T extends Comparable<T>> {

	private DNode<T> head;

	public DNode<T> getHead() {
		return head;
	}

	public void setHead(DNode<T> head) {
		this.head = head;
	}

	public Double_linked_list() {

		head = new DNode<>();

	}

	public void insert(T data) {
		DNode<T> newnode = new DNode<>(data);

		DNode<T> curr = head.getNext();
		for (; curr != null && curr.getData().compareTo(data) < 0 && curr.getNext() != null; curr = curr.getNext())
			;
		if (curr == null) {
			head.setNext(newnode);
		} else {
			if (curr.getPrev() == null && curr.getData().compareTo(data) >= 0)// at first // first
			{
				newnode.setNext(curr);
				curr.setPrev(newnode);
				head.setNext(newnode);

			} else if (curr.getNext() == null && curr.getData().compareTo(data) < 0)// al
																					// last
			{
				newnode.setPrev(curr);
				curr.setNext(newnode);

			} else// between
			{

				newnode.setNext(curr);
				newnode.setPrev(curr.getPrev());
				curr.getPrev().setNext(newnode);
				curr.setPrev(newnode);

			}

		}

	}

	public T delet(T data) {

		DNode<T> curr = head.getNext();
		for (; curr != null && curr.getData().compareTo(data) != 0; curr = curr.getNext())
			;
		if (curr == null) {
			return null;
		} else {
			if (curr.getPrev() == null)// at first // first
			{
				curr.getNext().setPrev(head);
				head.setNext(curr.getNext());

			} else if (curr.getNext() == null)// in last
			{
				System.out.println(data);
				System.out.println(curr.getPrev());
				curr.getPrev().setNext(null);

			} else// between
			{
				curr.getNext().setPrev(curr.getPrev());
				curr.getPrev().setNext(curr.getNext());

			}

		}
		return curr.getData();

	}

	public DNode<T> find(T data) {
		if (head.getNext() == null)
			return null;
		DNode<T> curr = head.getNext();
		while (curr != null && curr.getData().compareTo(data) <= 0) {
			if (curr.getData().compareTo(data) == 0)
				return curr;
			curr = curr.getNext();
		}
		return null;

	}

	public String toString() {
		String sum = "";

		DNode<T> curr = head.getNext();
		sum += "dommy -->";
		while (curr != null) {
			sum += curr + "<==>";
			curr = curr.getNext();
		}
		sum += "null";
		return sum;
	}

	public int length_ittrariv() {
		int counter = 0;
		DNode<T> curr = head;
		while (curr != null) {
			counter++;

			curr = curr.getNext();
		}
		return counter;

	}

	public int Length_recursion() {

		return Length_recursion(head);

	}

	private int Length_recursion(DNode<T> curr) {
		if (curr.getNext() == null)
			return 1;
		return 1 + Length_recursion(curr.getNext());

	}

	public void trafarc_Back() {
		if (head == null || head.getNext() == null) {
			return;
		}

		DNode<T> curr = head;
		System.out.print("null-->");
		while (curr.getNext() != null) {

			curr = curr.getNext();
		}
		while (curr != null) {

			System.out.print(curr + "-->");
			curr = curr.getPrev();
		}
		System.out.println("head");

	}

	public void trafarc_Back_rec() {

		if (head == null || head.getNext() == null) {
			return;
		}
		System.out.print("null" + "-->");
		trafarc_Back_rec(head);
		System.out.println("head");
	}

	private void trafarc_Back_rec(DNode<T> curr) {
		if (curr.getNext() == null) {
			System.out.print(curr + "-->");
			return;
		}
		trafarc_Back_rec(curr.getNext());
		System.out.print(curr + "-->");
	}

	public int count(T data) {
		DNode<T> curr = head;
		int counter = 0;
		while (curr != null) {
			if (curr.getData().compareTo(data) == 0)
				counter++;

			curr = curr.getNext();

		}

		return counter;
	}

	public void removeddoublicated() {
		if (head == null || head.getNext() == null) {
			return;
		}
		DNode<T> curr = head;
		while (curr.getNext() != null) {
			if (curr.getData().compareTo(curr.getNext().getData()) == 0) {
				if (curr.getNext().getNext() != null) {
					curr.getNext().getNext().setPrev(curr);
					curr.setNext(curr.getNext().getNext());
					continue;
				} else {

					curr.setNext(null);

					break;
				}

			}
			curr = curr.getNext();

		}

	}

	public void removeddoublicated_recAndreverce() {
		if (head == null || head.getNext() == null) {
			return;
		}
		removeddoublicated_rec(head);

		reverce(head);

	}

	private void reverce(DNode<T> curr) {

		if (curr.getNext() == null) {
			curr.setNext(curr.getPrev());
			curr.setPrev(curr.getNext());
			head = curr;

			return;
		}

		reverce(curr.getNext());

		curr.setNext(curr.getPrev());
		curr.setPrev(curr.getNext());

	}

	private void removeddoublicated_rec(DNode<T> curr) {

		if (curr.getNext() == null)
			return;
		if (curr.getData().compareTo(curr.getNext().getData()) == 0) {
			if (curr.getNext().getNext() != null) {

				curr.getNext().getNext().setPrev(curr);
				curr.setNext(curr.getNext().getNext());
				removeddoublicated_rec(curr);
				return;
			} else {

				curr.setNext(null);

				return;
			}

		}
		removeddoublicated_rec(curr.getNext());

	}

	public void traverce() {
		DNode<T> curr = head.getNext();
		System.out.print("dommy" + "-->");
		while (curr != null) {
			System.out.print(curr + "-->");
			curr = curr.getNext();
		}
		System.out.println("null");
	}

	public int count_rec(T data) {
		if (head == null)
			return 0;
		return count(data, head);
	}

	public int count(T data, DNode<T> curr) {
		if (curr == null) {
			return 0;
		}
		if (curr.getData().compareTo(data) == 0) {
			return 1 + count(data, curr.getNext());
		} else {
			return 0 + count(data, curr.getNext());
		}
	}
}
