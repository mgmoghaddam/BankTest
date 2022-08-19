package mostafa.mu.models;

public enum Currency {
  DOLLAR {
    @Override
    public String toString() {
      return "Dollar";
    }
  },
  EURO {
    @Override
    public String toString() {
      return "Euro";
    }
  },
  POUND {
    @Override
    public String toString() {
      return "Pound";
    }
  },
  YEN {
    @Override
    public String toString() {
      return "Yen";
    }
  },
  FRANC {
    @Override
    public String toString() {
      return "Franc";
    }
  }
}
