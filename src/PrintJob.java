public class PrintJob {
    private int id;
    private int numPages;
    private String owner;

    PrintJob(int id, int numPages, String owner) {
        this.id = id;
        this.numPages = numPages;
        this.owner = owner;
    }

    public String getOwner() {
        return this.owner;
    }

    public int getNumPages() {return this.numPages;}

    @Override
    public String toString() {
        return "Job " + this.id + " from " + this.owner + ": " + this.numPages + " pages";
    }
}
