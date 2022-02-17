package L5;

/**
 * @author cynyard
 * @description
 * @date 2/16/22
 */
public class InterCodeList {

    InterCode head = null;
    InterCode tail = null;
    private int size = 0;

    public InterCodeList() {

    }

    public InterCodeList(InterCode head) {
        if (head == null) {
            return;
        }
        this.head = head;
        this.tail = head;
        this.size++;
    }

    public static InterCodeList join(InterCodeList codeList1, InterCodeList codeList2) {
        if (codeList1 == null) {
            return codeList2;
        }
        codeList1.addInterCodeList(codeList2);
        return codeList1;
    }

    public void addInterCode(InterCode newCode) {
        if (newCode == null) {
            return;
        }
        if (this.isEmpty()) {
            this.head = newCode;
            this.tail = newCode;
            this.size++;
            return;
        }
        this.tail.next = newCode;
        this.tail = newCode;
        this.size++;
    }

    public void addInterCodeList(InterCodeList newInterCodeList) {
        if (newInterCodeList == null || newInterCodeList.isEmpty()) {
            return;
        }
        if (this.isEmpty()) {
            this.head = newInterCodeList.head;
            this.tail = newInterCodeList.tail;
            this.size = newInterCodeList.size;
            return;
        }
        this.tail.next = newInterCodeList.head;
        this.tail = newInterCodeList.tail;
        this.size += newInterCodeList.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }
}
