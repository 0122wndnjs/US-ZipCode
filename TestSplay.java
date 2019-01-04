/**
 * TestSplay class
 * 
 * @author Joowon Kim
 */

public class TestSplay {
	public static void main(String[] args) {
		splayTest();
	}

	public static void splayTest() {

		SplayTree<String> splay = new SplayTree<>();

		// insert
		splay.insert("Peculiar");
		insert("Peculiar");
		printingAll(splay);
		splay.insert("Crapo");
		insert("Crapo");
		printingAll(splay);
		splay.insert("Accident");
		insert("Accident");
		printingAll(splay);
		splay.insert("Eau Claire");
		insert("Eau Claire");
		printingAll(splay);
		splay.insert("Boring");
		insert("Boring");
		printingAll(splay);
		splay.insert("Hell");
		insert("Hell");
		printingAll(splay);
		splay.insert("Walla Walla");
		insert("Walla Walla");
		printingAll(splay);
		splay.insert("Surprise");
		insert("Surprise");
		printingAll(splay);
		splay.insert("Joseph");
		insert("Joseph");
		printingAll(splay);
		splay.insert("Romance");
		insert("Romance");
		printingAll(splay);
		splay.insert("Mars");
		insert("Mars");
		printingAll(splay);
		splay.insert("Nuttsville");
		insert("Nuttsville");
		printingAll(splay);
		splay.insert("Rough and Ready");
		insert("Rough and Ready");
		printingAll(splay);
		splay.insert("Dynamite");
		insert("Dynamite");
		printingAll(splay);
		splay.insert("Good Grief");
		insert("Good Grief");
		printingAll(splay);
		splay.insert("Zarephath");
		insert("Zarephath");
		printingAll(splay);

		// remove
		splay.remove("Eau Claire");
		remove("Eau Claire");
		printingAll(splay);
		splay.remove("Accident");
		remove("Accident");
		printingAll(splay);
		splay.remove("Rough and Ready");
		remove("Rough and Ready");
		printingAll(splay);
	}

	public static void printingAll(SplayTree<String> splay) {
 
		System.out.println("*****By Depth **************");
		splay.printByDepth();
		System.out.println("*********By Level ************");
		splay.printLevelOrder();
		System.out.println();

		System.out.println("Size = " + splay.getSize());
		System.out.println("Height = " + splay.getHeight());

		System.out.println();
		System.out.println();
	}

	public static void insert(String name) {

		System.out.println("***Insert " + name + "**************");
	}

	public static void remove(String name) {

		System.out.println("***Remove " + name + "**************");
	}

}
