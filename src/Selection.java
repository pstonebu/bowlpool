
public enum Selection {
    FAVORITE(0), UNDERDOG(1), EXTRA1(2), EXTRA2(3);

    private int id;

    Selection(int id) {
        this.id = id;
    }

    public int getID() {
        return id;
    }
}
