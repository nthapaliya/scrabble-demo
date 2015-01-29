import java.util.ArrayList;

public class Trie {
private class Node {
char value;
Node [] children;
boolean isWord;
Node parent;

Node (char value) {
        this.value = value;
        children = new Node [26];
        isWord = false;
        parent = null;
}

Node nextNode (char c) {
        c = Character.toLowerCase(c);
        if (children[c-'a'] != null)
                return children[c-'a'];
        else return null;
}
Node nextNode (int i) {   //just to make it easier
        char c = (char) i;
        return nextNode(c);
}
void addNode (char c) {
        children[c - 'a'] = new Node(c);
        children[c - 'a'].parent = this;
}
void markWord() {
        isWord = true;
}
boolean isWord(){
        return isWord;
}

boolean isLeaf() {
        for (int i = 0; i < 26; i++) {
                if (children[i]!=null) return false;
        }
        return true;
}
boolean hasChild (int i) {
        char c = (char) i;
        return children[i -'a'] != null;
}

}

private Node root;
ArrayList <String> a;

public Trie () {
        root = new Node(' ');
}
public void add (String s) {
        Node n = root;

        for (int i=0; i < s.length(); i++) {
                char c = s.charAt(i);

                if (n.nextNode(c) != null) n = n.nextNode(c);
                else {
                        n.addNode(c);
                        n = n.nextNode(c);
                }
        }
        n.markWord();
}

public boolean contains (String s) {
        s = s.toLowerCase();
        Node n = getNode (s);

        if (n != null) return n.isWord();
        else return false;
}

public ArrayList <String> printAll(){
        a = new ArrayList<String>();
        a = printFrom (root);
        return a;
}

public String getWord (Node n) {
        String s="";
        while (n.parent != null) {
                s = n.value + s;
                n = n.parent;
        }
        return s;
}

Node getNode (String s) {
        s = s.toLowerCase();
        Node n = root;

        for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (n.nextNode (c) != null) n = n.nextNode (c);
                else return null;
        }
        return n;
}
public ArrayList<String> getChildNodes (String s) {
        Node n;
        if (s.equals("")) n = root;
        else n = getNode (s);

        ArrayList <String> childNodes = new ArrayList<String>();

        for (int i = 0; i < 26; i++) {
                if (n.hasChild(i + 'a'))
                        childNodes.add("" + n.nextNode(i + 'a').value);
        }
        return childNodes;
}


public ArrayList<String> printFrom (String s) {
        a = new ArrayList<String>();
        s = s.toLowerCase();
        Node n = root;

        for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (n.nextNode (c) != null) n = n.nextNode (c);
        }
        a = printFrom (n);
        return a;
}

public boolean hasNextNode (String s, String l) {
        Node n = getNode (s);
        l = l.toLowerCase();
        return n.hasChild (l.charAt(0));
}

public ArrayList<String> printFrom (Node n) {
        if (n!=null) {
                if (n.isWord()) {
                        String str = getWord (n);
                        //System.out.println (str);
                        a.add(str);
                }
                for (int i = 0; i < 26; i++) {
                        printFrom (n.nextNode (i + 'a'));
                }

        }
        return a;
}


}
