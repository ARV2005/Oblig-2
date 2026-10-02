package no.oslomet.cs.algdat;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Objects;

public class DobbeltLenketListe<T> implements Liste<T> {
    // Innebygd (Trenger ikke endres)

    /**
     * Node class
     *
     * @param <T>
     */
    private static final class Node<T> {
        private T verdi;
        private Node<T> forrige, neste;

        private Node(T verdi, Node<T> forrige, Node<T> neste) {
            this.verdi = verdi; this.forrige = forrige; this.neste = neste;
        }
        private Node(T verdi) {this(verdi, null, null);}
    }

    private Node<T> hode;
    private Node<T> hale;
    private int antall;
    private int endringer;

    public void fraTilKontroll(int fra, int til) {
        if (fra < 0) throw new IndexOutOfBoundsException("fra("+fra+") er negativ.");
        if (til > antall) throw new IndexOutOfBoundsException("til("+til+") er større enn antall("+antall+")");
        if (fra > til) throw new IllegalArgumentException("fra("+fra+") er større enn til("+til+") - Ulovlig intervall.");
    }

    // Oppgave 0
    public static int gruppeMedlemmer() {
        return 1; // Returner hvor mange som er i gruppa deres
    }

    // Oppgave 1
    public DobbeltLenketListe() {
    }

    public DobbeltLenketListe(T[] a) {
        Objects.requireNonNull(a);

        for (T verdi : a) {
            if (verdi != null) {
                Node<T> ny = new Node<>(verdi);

                if (antall == 0) {
                    hode = hale = ny;
                } else {
                    ny.forrige = hale;
                    hale.neste = ny;
                    hale = ny;
                }

                antall++;
            }
        }
    }

    @Override
    public int antall() {
        return antall;
    }

    @Override
    public boolean tom() {
        return antall == 0;
    }
        // Oppgave 2
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<T> p = hode;

        while (p != null) {
            sb.append(p.verdi);

            if (p.neste != null) {
                sb.append(", ");
            }

            p = p.neste;
        }

        sb.append("]");
        return sb.toString();
    }

    public String omvendtString() {
        StringBuilder sb = new StringBuilder("[");
        Node<T> p = hale;

        while (p != null) {
            sb.append(p.verdi);

            if (p.forrige != null) {
                sb.append(", ");
            }

            p = p.forrige;
        }

        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean leggInn(T verdi) {
        Objects.requireNonNull(verdi);

        Node<T> ny = new Node<>(verdi);

        if (antall == 0) {
            hode = hale = ny;
        } else {
            ny.forrige = hale;
            hale.neste = ny;
            hale = ny;
        }

        antall++;
        endringer++;

        return true;
    }

    // Oppgave 3
    private Node<T> finnNode(int indeks) {
        if (indeks < antall / 2) {
            Node<T> p = hode;

            for (int i = 0; i < indeks; i++) {
                p = p.neste;
            }

            return p;
        } else {
            Node<T> p = hale;

            for (int i = antall - 1; i > indeks; i--) {
                p = p.forrige;
            }

            return p;
        }
    }

    @Override
    public T hent(int indeks) {
        indeksKontroll(indeks, false);
        return finnNode(indeks).verdi;
    }

    @Override
    public T oppdater(int indeks, T nyverdi) {
        Objects.requireNonNull(nyverdi);
        indeksKontroll(indeks, false);

        Node<T> p = finnNode(indeks);
        T gammelverdi = p.verdi;

        p.verdi = nyverdi;
        endringer++;

        return gammelverdi;
    }

    public Liste<T> subliste(int fra, int til) {
        fraTilKontroll(fra, til);

        DobbeltLenketListe<T> liste = new DobbeltLenketListe<>();

        if (fra == til) {
            return liste;
        }

        Node<T> p = finnNode(fra);

        for (int i = fra; i < til; i++) {
            liste.leggInn(p.verdi);
            p = p.neste;
        }

        return liste;
    }
        // Oppgave 4
    @Override
    public int indeksTil(T verdi) {
        Node<T> p = hode;
        int indeks = 0;

        while (p != null) {
            if (Objects.equals(p.verdi, verdi)) {
                return indeks;
            }

            p = p.neste;
            indeks++;
        }

        return -1;
    }

    @Override
    public boolean inneholder(T verdi) {
        return indeksTil(verdi) != -1;
    }

   // Oppgave 5
    @Override
    public void leggInn(int indeks, T verdi) {
        Objects.requireNonNull(verdi);
        indeksKontroll(indeks, true);

        Node<T> ny = new Node<>(verdi);

        if (antall == 0) {
            hode = hale = ny;
        } else if (indeks == 0) {
            ny.neste = hode;
            hode.forrige = ny;
            hode = ny;
        } else if (indeks == antall) {
            ny.forrige = hale;
            hale.neste = ny;
            hale = ny;
        } else {
            Node<T> p = finnNode(indeks);

            ny.forrige = p.forrige;
            ny.neste = p;

            p.forrige.neste = ny;
            p.forrige = ny;
        }

        antall++;
        endringer++;
    }

    // Oppgave 6
    @Override
    public T fjern(int indeks) {
        indeksKontroll(indeks, false);

        Node<T> p = finnNode(indeks);
        T verdi = p.verdi;

        if (p.forrige == null) {
            hode = p.neste;
        } else {
            p.forrige.neste = p.neste;
        }

        if (p.neste == null) {
            hale = p.forrige;
        } else {
            p.neste.forrige = p.forrige;
        }

        p.forrige = null;
        p.neste = null;

        antall--;
        endringer++;

        return verdi;
    }

    @Override
    public boolean fjern(T verdi) {
        Node<T> p = hode;

        while (p != null) {
            if (p.verdi.equals(verdi)) {

                if (p.forrige == null) {
                    hode = p.neste;
                } else {
                    p.forrige.neste = p.neste;
                }

                if (p.neste == null) {
                    hale = p.forrige;
                } else {
                    p.neste.forrige = p.forrige;
                }

                p.forrige = null;
                p.neste = null;

                antall--;
                endringer++;

                return true;
            }

            p = p.neste;
        }

        return false;
    }

    // Oppgave 7
    @Override
    public void nullstill() {
        throw new UnsupportedOperationException();
    }

    // Oppgave 8
    @Override
    public Iterator<T> iterator() {
        return new DobbeltLenketListeIterator();
    }
    
    public Iterator<T> iterator(int indeks) {
        indeksKontroll(indeks, false);
        return new DobbeltLenketListeIterator(indeks);
    }
    
    private class DobbeltLenketListeIterator implements Iterator<T> {
        private Node<T> denne;
        private boolean kanFjerne;
        private int iteratorendringer;
    
        private DobbeltLenketListeIterator() {
            denne = hode;
            kanFjerne = false;
            iteratorendringer = endringer;
        }
    
        private DobbeltLenketListeIterator(int indeks) {
            denne = finnNode(indeks);
            kanFjerne = false;
            iteratorendringer = endringer;
        }
    
        @Override
        public boolean hasNext() {
            return denne != null;
        }
    
        @Override
        public T next() {
            if (iteratorendringer != endringer) {
                throw new java.util.ConcurrentModificationException();
            }
    
            if (denne == null) {
                throw new java.util.NoSuchElementException();
            }
    
            T verdi = denne.verdi;
            denne = denne.neste;
            kanFjerne = true;
    
            return verdi;
        }

    // Oppgave 9:
    @Override
    public void remove() {
        throw new UnsupportedOperationException();
        }
    }

    // Oppgave 10
    public static <T> void sorter(Liste<T> liste, Comparator<? super T> c) {
        throw new UnsupportedOperationException();
    }
}
