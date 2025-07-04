public class PrintJob {
    private int id;
    private int numPages;
    private String owner;

    PrintJob(int id, int numPages, String owner) {
        this.id = 1;
        this.numPages = numPages;
        this.owner = owner;
    }
    public String getOwner() {
        return this.owner;
    }
    @Override
    public String toString() {
        return "Job " + this.id + " from " + this.owner + ": " + this.numPages + " pages";
    }
}
