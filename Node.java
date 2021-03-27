public class Node extends Cell {
    private Node top, topRight, right, bottomRight, bottom, bottomLeft, left, topLeft;
    
    public Node(int row, int column) {
        super(row, column);
        top = null;
        topRight = null;
        right = null;
        bottomRight = null;
        bottom = null;
        bottomLeft = null;
        left = null;
        topLeft = null;
    }
    public Node() {
        top = null;
        topRight = null;
        right = null;
        bottomRight = null;
        bottom = null;
        bottomLeft = null;
        left = null;
        topLeft = null;
    }
    
    public void setTop(Node top) {
        this.top = top;
    }
    public void setTopRight(Node topRight) {
        this.topRight = topRight;
    }
    public void setRight(Node right) {
        this.right = right;
    }
    public void setBottomRight(Node bottomRight) {
        this.bottomRight = bottomRight;
    }
    public void setBottom(Node bottom) {
        this.bottom = bottom;
    }
    public void setBottomLeft(Node bottomLeft) {
        this.bottomLeft = bottomLeft;
    }
    public void setLeft(Node left) {
        this.left = left;
    }
    public void setTopLeft(Node topLeft) {
        this.topLeft = topLeft;
    }
    
    public Node getTop() {
        return top;
    }
    public Node getTopRight() {
        return topRight;
    }
    public Node getRight() {
        return right;
    }
    public Node getBottomRight() {
        return bottomRight;
    }
    public Node getBottom() {
        return bottom;
    }
    public Node getBottomLeft() {
        return bottomLeft;
    }
    public Node getLeft() {
        return left;
    }
    public Node getTopLeft() {
        return topLeft;
    }
}