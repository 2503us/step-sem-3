package access.assignment_problems;

public class LibraryMember {

    private String membershipId;
    String branchCode;
    protected double finesOwed;
    public String displayName;

    public LibraryMember(String membershipId, String branchCode,
                         double finesOwed, String displayName) {

        String trimmedId = membershipId.trim();

        if (trimmedId.length() < 4) {
            throw new IllegalArgumentException("Invalid membership ID");
        }

        this.membershipId = trimmedId;
        this.branchCode = branchCode;
        this.finesOwed = finesOwed;
        this.displayName = displayName;
    }
}