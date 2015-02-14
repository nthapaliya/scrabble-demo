package wordsearch;

import java.util.Hashtable;

public class Dawg {
    private static final int OFFSET = 'a';
    private State root;
    private Hashtable<String, State> register;

    public Dawg(Iterable<String> list) {
        root = new State();
        AddFromAlphabetic(list);
    }

    private void AddFromAlphabetic(Iterable<String> list) {
        register = new Hashtable<String, State>();
        for (String s : list) {
            s = s.toLowerCase();
            State ps = getPrefixState(s);
            int index = getPrefixIndex(s);
            if (ps.hasChildren()) {
                replaceOrRegister(ps);
            }
            ps.addSuffix(s.substring(index));
        }
        replaceOrRegister(root);
    }

    private State getPrefixState(String s) {
        State st = root;
        for (char c : s.toCharArray()) {
            c -= OFFSET;
            if (st.edges[c] == null) {
                return st;
            }
            st = st.edges[c];
        }
        return st;
    }

    private int getPrefixIndex(String s) {
        State st = root;
        int i = 0;
        for (char c : s.toCharArray()) {
            c -= OFFSET;
            if (st.edges[c] == null) {
                return i;
            }
            st = st.edges[c];
            i++;
        }
        return i;
    }

    public boolean Contains(String s) {
        State st = root;
        for (char c : s.toLowerCase().toCharArray()) {
            c -= OFFSET;
            if (st.edges[c] == null) {
                return false;
            }
            st = st.edges[c];
        }
        return st.eow;
    }

//    private boolean verify() {
//        State st = root;
//        return false;
//    }

    private void replaceOrRegister(State st) {
        State lastChild = st.lastChild();
        if (lastChild.hasChildren()) {
            replaceOrRegister(lastChild);
        }
        String hsh = lastChild.getHash();
        if (register.containsKey(hsh)) {
            st.edges[st.lastIndex()] = register.get(hsh);
        } else {
            register.put(hsh, lastChild);
        }
    }

    private class State {
        State[] edges;
        boolean eow;
        String hash;

        State() {
            this.edges = new State[26];
            this.eow = false;
            this.hash = "";
        }

        String getHash() {
            if (!this.hash.equals("")) {
                return this.hash;
            }
            String s = "";

            if (this.eow) {
                s = "EOW";
            }
            for (State n : this.edges) {
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
            for (State s : this.edges) {
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
            for (char c : s.toCharArray()) {
                c -= OFFSET;
                st.edges[c] = new State();
                st = st.edges[c];
            }
            st.eow = true;
        }
    }

}
