package liste;

public class ListeSimple {
    private long size;
    Noeud tete;

    /**
     * Renvoie le nombre de nœuds de la liste.
     * @return le nombre de nœuds de la liste
     */
    public long getSize() {
        return size;
    }

    /**
     * Ajoute un nœud en tête de liste.
     * @param element la valeur du nœud à ajouter
     */
    public void ajout(int element) {
        tete = new Noeud(element, tete);
        size++;
    }

    /**
     * Remplace la première occurrence d'un nœud dans la liste.
     * La recherche s'effectue à partir de la tête de la liste.
     * La comparaison repose sur l'identité des objets.
     *
     * @param element l'objet à rechercher
     * @param nouvelleValeur la nouvelle valeur à affecter
     */
    public void modifiePremier(Object element, Object nouvelleValeur) {
        Noeud courant = tete;
        while (courant != null && courant.getElement() != element)
            courant = courant.getSuivant();
        if (courant != null)
            courant.setElement(nouvelleValeur);
    }


    /**
     * Remplace toutes les occurrences d'un nœud dans la liste.
     * La comparaison repose sur l'identité des objets.
     *
     * @param element l'objet à rechercher
     * @param nouvelleValeur la nouvelle valeur à affecter
     */
    public void modifieTous(Object element, Object nouvelleValeur) {
        Noeud courant = tete;
        while (courant != null) {
            if (courant.getElement() == element)
                courant.setElement(nouvelleValeur);
            courant = courant.getSuivant();
        }
    }

    /**
     * Renvoie une représentation textuelle de la liste.
     * Les nœuds sont séparés par des virgules et entourés de parenthèses
     * après le préfixe "ListeSimple".
     *
     * @return la représentation textuelle de la liste
     */
    public String toString() {
        StringBuilder sb = new StringBuilder("ListeSimple(");
        Noeud n = tete;
        while (n != null) {
            sb.append(n);
            n = n.getSuivant();
            if (n != null)
                sb.append(", ");
        }
        sb.append(")");
        return sb.toString();
    }

    /**
     * Supprime la première occurrence d'un nœud dans la liste.
     * La comparaison repose sur l'identité des objets.
     *
     * @param element l'objet à rechercher et à supprimer
     */
    public void supprimePremier(Object element) {
        if (tete != null) {
            if (tete.getElement() == element) {
                tete = tete.getSuivant();
                size--;
                return;
            }
            Noeud precedent = tete;
            Noeud courant = tete.getSuivant();
            while (courant != null && courant.getElement() != element) {
                precedent = precedent.getSuivant();
                courant = courant.getSuivant();
            }
            if (courant != null) {
                precedent.setSuivant(courant.getSuivant());
                size--;
            }
        }
    }

    /**
     * Supprime toutes les occurrences de la valeur indiquée dans la liste.
     *
     * @param element la valeur à supprimer
     */
    public void supprimeTous(int element) {
       tete = supprimeTousRecurs(element, tete);
    }

    /**
    * Supprime récursivement toutes les occurrences d'un nœud
    * dans la sous-liste dont la tête est fournie.
    *
    * @param element l'objet à rechercher et à supprimer
    * @param tete la tête de la sous-liste à traiter
    * @return la nouvelle tête de la sous-liste après suppression
    */
    public Noeud supprimeTousRecurs(Object element, Noeud tete) {
        if (tete != null) {
            Noeud suiteListe = supprimeTousRecurs(element, tete.getSuivant());
            if (tete.getElement() == element) {
                size--;
                return suiteListe;
            } else {
                tete.setSuivant(suiteListe);
                return tete;
            }
        } else return null;
    }

    /**
     * Renvoie l'avant-dernier nœud de la liste.
     *
     * @return l'avant-dernier nœud, ou null si la liste contient
     * moins de deux nœuds
     */
    public Noeud getAvantDernier() {
        if (tete == null || tete.getSuivant() == null)
            return null;
        else {
            Noeud courant = tete;
            Noeud suivant = courant.getSuivant();
            while (suivant.getSuivant() != null) {
                courant = suivant;
                suivant = suivant.getSuivant();
            }
            return courant;
        }
    }

    /**
     * Inverse l'ordre des nœuds de la liste.
     */
    public void inverser() {
        Noeud precedent = null;
        Noeud courant = tete;
        while (courant != null) {
            Noeud next = courant.getSuivant();
            courant.setSuivant(precedent);
            precedent = courant;
            courant = next;
        }
        tete = precedent;
    }

    /**
     * Renvoie le nœud précédant le nœud spécifié.
     *
     * @param r le nœud dont on recherche le prédécesseur
     * @return le nœud précédent, ou null si r est la tête
     * @throws IllegalArgumentException si r n'appartient pas à la liste
     */
    public Noeud getPrecedent(Noeud r) {
    // la liste n'est pas vide puisqu'on transmet un Node de la liste et le Node existe obligatoirement
        Noeud precedent = tete;
        Noeud courant = precedent.getSuivant();
        while (courant != r) {
            precedent = courant;
            courant = courant.getSuivant();
        }
        return precedent;
    }

    /**
     * Échange les positions de deux nœuds dans la liste.
     *
     * @param r1 le premier nœud à échanger
     * @param r2 le second nœud à échanger
     */
    public void echanger(Noeud r1, Noeud r2) {
        if (r1 == r2)
            return;
        Noeud precedentR1;
        Noeud precedentR2;
        if (r1 != tete && r2 != tete) {
            precedentR1 = getPrecedent(r1);
            precedentR2 = getPrecedent(r2);
            precedentR1.setSuivant(r2);
            precedentR2.setSuivant(r1);
        } else if (r1 == tete) {
            precedentR2 = getPrecedent(r2);
            precedentR2.setSuivant(tete);
            tete = r2;
        }
        else {
            precedentR1 = getPrecedent(r1);
            precedentR1.setSuivant(tete);
            tete = r1;
        }
        Noeud temp = r2.getSuivant();
        r2.setSuivant(r1.getSuivant());
        r1.setSuivant(temp);
    }

}