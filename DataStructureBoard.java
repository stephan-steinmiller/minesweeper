public class DataStructureBoard extends Board {
    private int columns, rows;
    private Node start;
    
    public DataStructureBoard(int columns, int rows, GUI gui) {
        super(columns,rows,gui);
        this.columns = columns;
        this.rows = rows;
        createStructure();
    }
    
    private void createStructure() {
        for(int i = 0; i<rows; i++)
            createRow(i);
    }
    
    private void createRow(int i) {
        Node currentNode;
        Node previousNode = new Node();
        
        for(int j = 0; j<columns; j++){
            if(i == 0 && j == 0) {
                start = new Node(i,j);
                
                previousNode = start;
            } else if(i > 0 && j == 0) {
                currentNode = new Node(i,j);
                appendAtBottom(currentNode, i);
                
                previousNode = currentNode;
            } else {
                currentNode = new Node(i,j);
                previousNode.setRight(currentNode);
                currentNode.setLeft(previousNode);
                if(i > 0) {
                    linkAllNodes(previousNode, i);
                }
                
                previousNode = currentNode;
            }
        }
    }
    
    private void linkAllNodes(Node currentNode, int i) {
        Node topRightNode = currentNode.getTop().getRight();
        topRightNode.setBottom(currentNode.getRight());
        currentNode.setTopRight(topRightNode);
        
        currentNode.getTop().setBottomRight(currentNode.getRight());
        currentNode.getRight().setTopLeft(currentNode.getTop());
        
        topRightNode.setBottomLeft(currentNode);
        currentNode.getRight().setTop(topRightNode);
    }
    
    private void appendAtBottom(Node node, int i) {
        Node nodeToAppendTo = getNodeDownwards(start, i-1);
        nodeToAppendTo.setBottom(node);
        node.setTop(nodeToAppendTo);
    }
    
    private Node getNodeDownwards(Node node, int i) {
        for(int k = 1; k <= i; k++)
            node = node.getBottom();
        return node;
    }
    
    private Node getNodeRightwards(Node node, int j) {
        for(int k = 1; k <= j; k++)
            node = node.getRight();
        return node;
    }
    
    @Override
    public void calculateValues(int i, int j) {
        long start = System.nanoTime();
        steps = 0;
        calculateValuesOfNode(getNode(i,j));
        long finish = System.nanoTime();
        long timeElapsed = finish - start;
        System.out.println("Steps Datastructure:"+steps);
        System.out.println("Time Datastructure:"+timeElapsed);
    }
    public void calculateValuesOfNode(Node node) {
        // Tiefensuche mit Hilfe von Rekursion
        if(!node.getValueIsCalculated())
            calculateValue(node);
        for(int l = 0; l < 8; l++) {
            Node nodeToCalculateValuesOf = getLinkedNodeByIndex(node, l);
            if(nodeToCalculateValuesOf != null && !nodeToCalculateValuesOf.getValueIsCalculated())
                calculateValuesOfNode(nodeToCalculateValuesOf);
        }
    }
    private void calculateValue(Node node) {
        for(int l = 0; l < 8; l++) {
            Node nodeToCalculateValueOf = getLinkedNodeByIndex(node, l);
            if(nodeToCalculateValueOf != null && nodeToCalculateValueOf.isMine())
                node.addToValue(1);
        }
        node.setValueIsCalculated(true);
    }
    
    // @Override
    // public void findAllNeighboredZeros(int i, int j) {
        // for(int l = 0; l < 8; l++) {
            // Node nodeToFindNeighboursOf = getLinkedNodeByIndex(getNode(i,j), l);
            // if(nodeToFindNeighboursOf != null && !nodeToFindNeighboursOf.isOpen()) {
                // openCell(nodeToFindNeighboursOf.getColumn(),nodeToFindNeighboursOf.getRow(),nodeToFindNeighboursOf);
                // findAllNeighbouredZerosFromNode(nodeToFindNeighboursOf);
            // }
        // }
    // }
    // private void findAllNeighbouredZerosFromNode(Node node) {
        // // Tiefensuche mit Hilfe von Rekursion
        // System.out.println("pls");
        // if(node.getValue() == 0) {
            // System.out.println("bitte funktionier");
            // for(int l = 0; l < 8; l++) {
                // Node nodeToFindNeighboursOf = getLinkedNodeByIndex(node, l);
                // if(nodeToFindNeighboursOf != null && !nodeToFindNeighboursOf.isOpen()) {
                    // openCell(nodeToFindNeighboursOf.getColumn(),nodeToFindNeighboursOf.getRow(),nodeToFindNeighboursOf);
                    // findAllNeighbouredZerosFromNode(nodeToFindNeighboursOf);
                // }
            // }
        // }
    // }
    
    private Node getLinkedNodeByIndex(Node node, int index) {
        steps++;
        switch(index) {
            case 0: return node.getTop();
            case 1: return node.getTopRight();
            case 2: return node.getRight();
            case 3: return node.getBottomRight();
            case 4: return node.getBottom();
            case 5: return node.getBottomLeft();
            case 6: return node.getLeft();
            case 7: return node.getTopLeft();
            default:return null;
        }
    }
    @Override
    public Cell getCell(int i, int j) {
        return getNode(i, j);
    }
    private Node getNode(int i, int j) {
        return getNodeRightwards(getNodeDownwards(start, i),j);
    }
}