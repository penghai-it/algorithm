package it.ph.com.DataStructures.linkedlist;

/**
 * @author: P H
 * @时间: 2024/03/15
 * @描述: 单向链表实现（不考虑编号的顺序）
 */
public class SingleLinkedListDemo {

    public static void main(String[] args) {
        HeroNode heroNode1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode heroNode2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode heroNode3 = new HeroNode(3, "吴用", "智多星");
        HeroNode heroNode4 = new HeroNode(4, "林冲", "豹子头");
        //按添加的顺序插入
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        singleLinkedList.add(heroNode1);
        singleLinkedList.add(heroNode2);
        singleLinkedList.add(heroNode3);
        singleLinkedList.add(heroNode4);
        //遍历
        singleLinkedList.list();
        System.out.println("============================================================================");
        //按编号顺序进行插入
        SingleLinkedList singleLinkedList2 = new SingleLinkedList();
        singleLinkedList2.addOrderBy(heroNode1);
        singleLinkedList2.addOrderBy(heroNode4);
        singleLinkedList2.addOrderBy(heroNode2);
        singleLinkedList2.addOrderBy(heroNode1);
        singleLinkedList2.addOrderBy(heroNode3);
        singleLinkedList2.list();
    }
}

class SingleLinkedList {
    //先初始化一个头节点，头节点不要动，不存放具体的数据(不能改变所以要用private)
    private HeroNode head = new HeroNode(0, "", "");

    //添加节点到单向链表（按添加顺序进行添加）
    //1、找到当前链表最后一个节点
    //2、将最后一个节点的next指向新的节点
    public void add(HeroNode heroNode) {
        //先定义一个变量来获取最后一个节点
        HeroNode temp = head;
        while (temp.next != null) {
            //在没有找到追后一个节点前，将temp后移
            temp = temp.next;
        }
        //将最后的一个节点的next指向新的节点
        temp.next = heroNode;
    }

    //按编号的顺序进行添加，如果编号存在给出提示不再添加
    public void addOrderBy(HeroNode heroNode) {
        //头节点不能变，定义一个变量来记录
        HeroNode temp = head;
        //再定义一个变量记录是否重复添加
        boolean flag = false;
        while (true) {
            //判断节点是否为空,如果为空就表示为最后一个节点，直接添加即可
            if (temp.next == null) {
                break;
            } else if (temp.next.no == heroNode.no) {
                //编号存在了
                flag = true;
                break;
                //判断当前节点的后一个节点的编号是否大于新添加节点的编号
            } else if (temp.next.no > heroNode.no) {
                break;
            }
            //节点向后移动
            temp = temp.next;
        }
        if (flag) {
            System.out.println("编号" + heroNode.no + "已经存在，不能重复添加");
        } else {//插入新节点
            //新节点的next=当前节点的next
            heroNode.next = temp.next;
            //当前节点的next=新节点
            temp.next = heroNode;
        }
    }

    //显示链表
    public void list() {
        //先判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //因为头节点不能动，因此需要一个辅助变量来遍历
        HeroNode temp = head.next;
        while (temp != null) {
            //判断是不是最后一个节点，如果是就直跳出循环
            System.out.println(temp);
            //没遍历一次节点先后移动一个
            temp = temp.next;
        }
    }
}

class HeroNode {
    public int no;
    public String name;
    public String nikname;
    //指向下一个节点
    public HeroNode next;

    public HeroNode(int no, String name, String nikname) {
        this.no = no;
        this.name = name;
        this.nikname = nikname;
    }

    @Override
    public String toString() {
        return "HeroNode{" + "no=" + no + ", name='" + name + '\'' + ", nikname='" + nikname + '\'' + '}';
    }
}