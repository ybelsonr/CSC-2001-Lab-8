public record BST<T extends Comparable <T>> (T value, BST<T> left, BST<T> right) {

    public static <T extends Comparable <T>> BST<T> insert(BST<T> tree, T v){
        return switch(tree){
            case null -> new BST<>(v, null, null);
            case BST<T>(T value, BST<T> l, BST<T> r) -> {
                int a = v.compareTo(value);
                if(a < 0 ){
                    yield new BST<>(value, insert(l, v), r);
                } else if(a > 0){
                    yield new BST<>(value, l, insert(r, v));
                }
                yield new BST<>(v, l, r);
            }

        };
    }

    public static <T extends Comparable <T>> T search(BST<T> tree, T a) {
        return switch (tree) {
            case null -> null;
            case BST<T>(T v, BST<T> l, BST<T> r) -> {
                int x = a.compareTo(v);
                if (x < 0) {
                    yield search(l, a);
                } else if (x > 0) {
                    yield search(r, a);
                } else {
                    yield v;
                }
            }
        };

    }

    public static <T extends Comparable <T>> BST<T> delete(BST<T> tree, T a) {
        return switch (tree) {
            case null -> null;
            case BST<T>(T v, BST<T> l, BST<T> r) -> {
                int x = a.compareTo(v);
                if (x < 0) {
                    yield new BST<T>(v, delete(l, a), r);
                } else if (x > 0) {
                    yield new BST<T>(v, l, delete(r, a));
                } else if(l == null){
                    yield r;
                } else if(r == null){
                    yield l;
                } else {
                    T successor = minValue(r);
                    yield new BST<>(successor, l, delete(r, successor));
                }
            }
        };

    }

    private static <T extends Comparable<T>> T minValue(BST<T> tree) {
        BST<T> current = tree;
        while (current.left() != null) {
            current = current.left();
        }
        return current.value();
    }

    public static Spot greatestLowerBound(BST<Spot> tree, double longitude) {
        return switch (tree) {
            case null -> null;
            case BST<Spot>(Spot v, BST<Spot> l, BST<Spot> r) -> {
                if (v.longitude() > longitude) {
                    yield greatestLowerBound(l, longitude);
                }
                Spot better = greatestLowerBound(r, longitude);
                if (better != null) {
                    yield better;
                } else {
                    yield v;
                }
            }
        };
    }

}
