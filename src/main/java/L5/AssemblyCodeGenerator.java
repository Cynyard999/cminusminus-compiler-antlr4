package L5;

/**
 * @author cynyard
 * @description
 * @date 1/16/22
 */
public class AssemblyCodeGenerator {

    private final InterCode interCodeHead;

    public AssemblyCodeGenerator(InterCode interCodeHead) {
        this.interCodeHead = interCodeHead;
    }

    public void start() {
        initDataSegment();
        initCodeSegment();
        InterCode currentInterCode = interCodeHead;
        while (currentInterCode != null) {
            // TODO
            currentInterCode = currentInterCode.next;
        }
    }

    private void initDataSegment() {
        OutputHelper.println(".data");
        OutputHelper.println("_prompt: .asciiz \"Enter an integer:\"");
        OutputHelper.println("_ret: .asciiz \"\\n\"");
        OutputHelper.println(".globl main");
    }

    private void initCodeSegment() {
        OutputHelper.println(".text");
        insertReadFunction();
        insertWriteFunction();
    }

    private void insertReadFunction() {
        OutputHelper.println("read:");
        OutputHelper.println("li $v0, 4", 2);
        OutputHelper.println("la $a0, _prompt", 2);
        OutputHelper.println("syscall", 2);
        OutputHelper.println("li $v0, 5", 2);
        OutputHelper.println("syscall", 2);
        OutputHelper.println("jr $ra", 2);
        OutputHelper.println();
    }

    private void insertWriteFunction() {
        OutputHelper.println("write:");
        OutputHelper.println("li $v0, 1", 2);
        OutputHelper.println("syscall", 2);
        OutputHelper.println("li $v0, 4", 2);
        OutputHelper.println("la $a0, _ret", 2);
        OutputHelper.println("syscall", 2);
        OutputHelper.println("move $v0, $0", 2);
        OutputHelper.println("jr $ra", 2);
        OutputHelper.println();
    }

}
