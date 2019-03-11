/**
 * 基于单链表LRU算法
 *
 * @author zoujp
 * @create 2019-03-11
 */
public class SingLinkedListLRU {

    class Node{
      private  Node next;
      private  String value;
      private final int max_size = 5;
      private int length=0;

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public void setValue(String value) {
            this.value = value;
        }

    }
    private  Node sourceNode = new Node(); //初始化

    public Node add(String value){
        Node tempNode=sourceNode;
        Node targetNode = new Node();
        targetNode.setValue(value);
        //在首位的情况
        if(value.equals(tempNode.value)){
            return tempNode;
        }
        //不在首位，重复的情况
        reapt(tempNode,value);
        //刷新临时变量
        tempNode=sourceNode;
        //不重复的情况,判断是否满了
        unReapt(tempNode,targetNode);
        // 赋值给sourceNode 全局的 以便下次使用
        sourceNode=targetNode;
        return targetNode;
    }
   

    //判断是否重复，出现重复的话就将重复元素后的链表覆盖掉重复元素的位置，同时长度-1，将添加的元素放到后面处理
    public void reapt(Node tempNode,String value){
        while (tempNode.next!=null){
            // 出现相同的情况 移动
            if(value.equals(tempNode.next.value)){
                tempNode.setNext(tempNode.getNext().getNext());
                sourceNode.length--;
            }else{
                tempNode=tempNode.next;
            }
        }
    }
    // 不重复的情形下，判断链表长度是否满了，
    //满了的话 删除链表最后一个，添加新的，记得把长度也赋值好
    //没有满就直接再链表头加一个 长度+1
    public void unReapt(Node tempNode,Node targetNode){
        int max= tempNode.max_size;
        if(tempNode.length==max){
            //最后一个删除掉
            while(tempNode.next!=null){
                if(tempNode.next.next==null){
                    tempNode.setNext(null);
                }
                else{
                    tempNode=tempNode.next;
                }
            }
            targetNode.next=sourceNode;
            targetNode.length=sourceNode.length;
        }else{
            targetNode.next=tempNode;
            tempNode.length=tempNode.length+1;
            targetNode.length=tempNode.length;
        }
    }

    public static void main(String[] args) {
        SingLinkedListLRU lru = new SingLinkedListLRU();
        lru.sourceNode.setValue("0");
        lru.sourceNode.length=1;
        //接收控制台输入的参数
        Scanner sc = new Scanner(System.in);
        while(true){
            lru.add(sc.next());
            printAll(lru.sourceNode);
        }
    }

    public static void  printAll(Node sourceNode){
        System.out.print(sourceNode.value);
        while(sourceNode.next!=null){
            System.out.print(","+sourceNode.next.value);
            sourceNode=sourceNode.next;
        }
        System.out.println();
    }
}
