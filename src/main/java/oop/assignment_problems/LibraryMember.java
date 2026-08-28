package oop.assignment_problems;

public class LibraryMember {

    static String name;
    static String memberId;
    static int booksIssued;

    String fixedName;
    String fixedMemberId;
    int fixedBooksIssued;

    static String libraryName = "Central Library";
    static int memberCount = 0;

    /*
     * name should not be static because each member has a different name.
     * memberId should not be static because each member needs a different ID.
     * booksIssued should not be static because each member can have a different number of books.
     */

    LibraryMember(String name, int booksIssued) {
        LibraryMember.name = name;
        LibraryMember.booksIssued = booksIssued;
        LibraryMember.memberId = "LM-" + booksIssued;
    }

    LibraryMember(String name, int booksIssued, boolean fixed) {
        this.fixedName = name;
        this.fixedBooksIssued = booksIssued;

        memberCount++;
        this.fixedMemberId = "LM-" + (1000 + memberCount);
    }

    void printBrokenMemberCard() {
        System.out.println(
                LibraryMember.name + " | " + LibraryMember.memberId
        );
    }

    void printMemberCard() {
        System.out.println(
                fixedName + " | " + fixedMemberId
        );
    }

    static void printTotalMembers() {
        System.out.println("Total members: " + memberCount);
    }

    public static void main(String[] args) {

        LibraryMember broken1 =
                new LibraryMember("Aditi", 2);

        LibraryMember broken2 =
                new LibraryMember("Rohan", 3);

        System.out.println("Broken version:");

        broken1.printBrokenMemberCard();
        broken2.printBrokenMemberCard();

        LibraryMember member1 =
                new LibraryMember("Aditi", 2, true);

        LibraryMember member2 =
                new LibraryMember("Rohan", 3, true);

        System.out.println();
        System.out.println("Fixed version:");

        member1.printMemberCard();
        member2.printMemberCard();

        LibraryMember.printTotalMembers();
    }
}