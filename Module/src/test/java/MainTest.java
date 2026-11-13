import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    static Spot spot(String name, double longitude) {
        return new Spot(name, 0, longitude);
    }

    static BST<Spot> build(double... longitudes) {
        BST<Spot> tree = null;
        for (double lon : longitudes) {
            tree = BST.insert(tree, spot("a" + lon, lon));
        }
        return tree;
    }

    @Test
    public void testCompareTo() {
        assertEquals(-1, spot("a",10).compareTo(spot("b", 20)));
        assertEquals(1, spot("a", 20).compareTo(spot("b", 10)));
        assertEquals(0, spot("a", 10).compareTo(spot("b", 10)));
    }

    @Test
    public void testRandomSpot() {
        for (int i = 0; i < 1000; i++) {
            Spot s = Spot.randomSpot();
            assertEquals(4, s.name().length());
            assertTrue(s.longitude() >= -180 && s.longitude() <= 180);
            assertTrue(s.latitude() >= -90 && s.latitude() <= 90);
        }
    }

    @Test
    public void testInsert() {
        assertEquals(new BST<>(spot("a", 5), null, null), BST.insert(null, spot("a", 5)));
        BST<Spot> tree = build(50, 30, 70);
        assertEquals(spot("a50.0", 50), tree.value());
        assertEquals(spot("a30.0", 30), tree.left().value());
        assertEquals(spot("a70.0", 70), tree.right().value());
    }

    @Test
    public void testSearch() {
        BST<Spot> tree = build(50, 30, 70, 20);
        assertEquals("a20.0", BST.search(tree, spot("a", 20)).name());
        assertEquals("a70.0", BST.search(tree, spot("a", 70)).name());
        assertEquals("a50.0", BST.search(tree, spot("a", 50)).name());
        assertNull(BST.search(tree, spot("a", 99)));
        assertNull(BST.search(null, spot("a", 1)));
    }

    @Test
    public void testGreatestLowerBound() {
        BST<Spot> tree = build(50, 30, 70, 20, 40, 60, 80);
        assertEquals(40.0, BST.greatestLowerBound(tree, 45).longitude(), 0.0);
        assertEquals(80.0, BST.greatestLowerBound(tree, 170).longitude(), 0.0);
        assertEquals(60.0, BST.greatestLowerBound(tree, 69.9).longitude(), 0.0);
        assertEquals(50.0, BST.greatestLowerBound(tree, 50).longitude(), 0.0);
        assertEquals(20.0, BST.greatestLowerBound(tree, 20).longitude(), 0.0);
    }

    @Test
    public void testDelete() {
        BST<Spot> tree = BST.delete(build(50, 30, 70), spot("a", 30));
        assertNull(BST.search(tree, spot("a", 30)));
        assertNull(tree.left());
    }

}
