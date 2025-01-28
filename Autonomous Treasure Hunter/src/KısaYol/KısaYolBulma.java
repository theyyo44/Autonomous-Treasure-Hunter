package KısaYol;

import entity.Player;
import main.GamePanel;

import javax.swing.text.html.parser.Entity;
import java.util.ArrayList;

public class KısaYolBulma {

    GamePanel gp;
    Node[][] node;
    ArrayList <Node> openList = new ArrayList<>();
    public ArrayList<Node>pathList = new ArrayList<>();
    Node startNode, goalNode, currrentNode;
    boolean goalReached = false;
    int step = 0;


    public KısaYolBulma(GamePanel gp){
        this.gp=gp;
        anllıkNode();
    }
    public void anllıkNode(){

        node = new Node[gp.maxWorldCol][gp.maxWorldRow];

        int col = 0;
        int row = 0;

        while (col<gp.maxWorldCol && row< gp.maxWorldRow){

            node[col][row] = new Node(col,row);

            col++;

            if (col == gp.maxWorldCol) {
                col = 0;
                row++;
            }
        }

    }

    public void resetNode(){
        int col = 0;
        int row = 0;

        while (col<gp.maxWorldCol && row< gp.maxWorldRow){

            node[col][row].open = false;
            node[col][row].checked = false;
            node[col][row].solid = false;

            col++;
            if(col == gp.maxWorldCol) {
                col = 0;
                row++;
            }

        }
        //Reset
        openList.clear();
        pathList.clear();
        goalReached = false;
        step = 0;

    }

    public void setNode(int startCol, int startRow, int goalCol, int goalRow, Player entity) {

        resetNode();

        startNode = node[startCol][startRow];
        currrentNode = startNode;
        goalNode = node[goalCol][goalRow];

        openList.add(currrentNode);

        int col = 0;
        int row = 0;
        while (col< gp.maxWorldCol && row< gp.maxWorldRow) {

            //set solıd node
            //check tıles
            int tileNum = gp.tileM.mapTileNumber[col][row];
            if(gp.tileM.tile[tileNum].collision == true) {
                node[col][row].solid = true;
            }
            getCost(node[col][row]);

            col++;
            if(col == gp.maxWorldCol) {
                col = 0;
                row++;
            }
        }

    }

    public void getCost(Node node){
        //G cost
        int xUzaklık = Math.abs(node.col - startNode.col);
        int yUzaklık = Math.abs(node.row - startNode.row);
        node.gCost = xUzaklık + yUzaklık;
        //H cost
        xUzaklık = Math.abs(node.col - goalNode.col);
        yUzaklık = Math.abs(node.row - goalNode.row);
        node.hCost = xUzaklık + yUzaklık;

        // F COST

        node.fCost = node.gCost +node.hCost;
    }

    public boolean search() {
        while (goalReached == false && step < 5000) {
            int col = currrentNode.col;
            int row = currrentNode.row;

            //Check THE cURRRENT nODE
            currrentNode.checked = true;
            openList.remove(currrentNode);
            //open the up node
            if((row-1)>=0){
                openNode(node[col][row-1]);
            }
            // Open the left node
            if(col - 1 >= 0){
                openNode(node[col-1][row]);
            }
            //open the down node
            if( row + 1 < gp.maxWorldRow){
                openNode(node[col][row+1]);
            }
            // open the right node
            if(col + 1 < gp.maxWorldCol){
                openNode(node[col+1][row]);
            }
            int bestNodeIndeks =0;
            int bestNodefCost = 999;

            for (int i = 0; i < openList.size(); i++) {

                //Check if ehis node'S Fcost is better
                if (openList.get(i).fCost < bestNodefCost){
                    bestNodeIndeks = i;
                    bestNodefCost = openList.get(i).fCost;
                }
                //ıf F cost is equal, check the G cost
                else if(openList.get(i).fCost == bestNodefCost){
                    if(openList.get(i).gCost < openList.get(bestNodeIndeks).gCost){
                        bestNodeIndeks= i;
                    }
                }
            }
            //If there is no node in the openList, end the loop
            if (openList.size() == 0){
                break;
            }

            //After the loop, openList(bestNodeIndeks) is the next step (=currentNode)
            currrentNode = openList.get(bestNodeIndeks);

            if (currrentNode == goalNode) {
                goalReached = true;
                trackThePath();
            }
            step++;
        }

        return  goalReached;
    }
    public void openNode(Node node){


        if(node.open == false && node.checked == false && node.solid ==false){
            node.open = true;
            node.parent = currrentNode;
            openList.add(node);
        }
    }
    public void trackThePath() {
        Node current = goalNode;
        while (current != startNode) {
            pathList.add(0,current);
            current= current.parent;
        }
    }
}

