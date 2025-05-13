package pl.training.module04_05.model;

public enum Currency {

    PLN {
        @Override
        public String toString() {
            return "zł";
        }
    },

    EUR {
        @Override
        public String toString() {
            return "€";
        }
    },

    USD {
        @Override
        public String toString() {
            return "$";
        }
    }

    /*@Override
    public String toString() {
        return switch (this) {
            case PLN -> "zł";
            case EUR -> "€";
            case USD -> "$";
        };
    }*/

}
