import java.util.Hashtable;

public class Dawg {
  private class State {
    State [] edges;
    boolean eow;
    String hash;

    State () {
      this.edges = new State [26];
      this.eow = false;
      this.hash = "";
    }

    String getHash() {
      if (this.hash != "") {
        return this.hash;
      }
      String s = "";

      if (this.eow) {
        s = "EOW";
      }
      for (State n: this.edges) {
        if (n == null) {
          s += "nil";
        } else {
          s += n.getHash();
        }
      }
      this.hash = Integer.toString(s.hashCode());
      return this.hash;
    }

    boolean hasChildren() {
      for (State s: this.edges) {
        if (s != null) {
          return true;
        }
      }
      return false;
    }

    State lastChild() {
      for (int i = 25; i >= 0; i--) {
        if (this.edges[i] != null) {
          return this.edges[i];
        }
      }
      return null;
    }

    int lastIndex() {
      for (int i = 25; i >= 0; i--) {
        if (this.edges[i] != null) {
          return i;
        }
      }
      return 0;
    }

    void addSuffix(String s) {
      State st = this;
      for (char c: s.toCharArray()) {
        c -= OFFSET;
        st.edges[c] = new State();
        st = st.edges[c];
      }
      st.eow = true;
    }
  }
    static Hashtable<String, State> register;
    final int OFFSET = 97;
    State root;

    public Dawg() {
      this.root = new State();
    }

    public void AddFromAlphabetic(Iterable<String> list) {
      register = new Hashtable<String,State>();
      for (String s: list) {
        s = s.toLowerCase();
        State ps = this.getPrefixState(s);
        int index = this.getPrefixIndex(s);
        if (ps.hasChildren()) {
          Dawg.replaceOrRegister(ps);
        }
        ps.addSuffix(s.substring(index));
      }
      Dawg.replaceOrRegister(this.root);
      if (!this.verify()) {
        System.out.println("algo didn't work");
      }
      System.out.println(register.size());
    }

    State getPrefixState(String s) {
      State st = this.root;
      for (char c: s.toCharArray()) {
        c -= OFFSET;
        if (st.edges[c] == null) {
          return st;
        }
        st = st.edges[c];
      }
      return st;
    }

    int getPrefixIndex(String s) {
      State st = this.root;
      int i = 0;
      for (char c: s.toCharArray()) {
        c -= OFFSET;
        if (st.edges[c] == null) {
          return i;
        }
        st = st.edges[c];
        i++;
      }
      return i;
    }

    boolean contains (String s) {
      State st = this.root;
      for (char c: s.toLowerCase().toCharArray()) {
        c -= OFFSET;
        if (st.edges[c] == null) {
          return false;
        }
        st = st.edges[c];
      }
      return st.eow;
    }

    boolean verify() {
      State st = this.root;
      return false;
    }

    static void replaceOrRegister(State st) {
      State lastchild = st.lastChild();
      if (lastchild.hasChildren()) {
        replaceOrRegister(lastchild);
      }
      String hsh = lastchild.getHash();
      if (register.containsKey(hsh)) {
        st.edges[st.lastIndex()] = register.get(hsh);
      } else {
        register.put(hsh, lastchild);
      }
    }
}
