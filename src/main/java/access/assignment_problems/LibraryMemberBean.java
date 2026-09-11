package access.assignment_problems;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class LibraryMemberBean {

    private String membershipId;
    private String name;
    private boolean premiumMember;
    private String securityAnswer;

    public LibraryMemberBean() {
        this(null, null);
    }

    public LibraryMemberBean(String name) {
        this(null, name);
    }

    public LibraryMemberBean(String membershipId, String name) {
        this.membershipId = membershipId;
        this.name = name;
    }

    public String getMembershipId() {
        return membershipId;
    }

    public void setMembershipId(String id) {

        if (membershipId == null) {
            membershipId = id;
        }
    }

    public boolean isPremiumMember() {
        return premiumMember;
    }

    public void setPremiumMember(boolean premium) {
        premiumMember = premium;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSecurityAnswer(String answer) {

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            byte[] hash = md.digest(
                    answer.getBytes(StandardCharsets.UTF_8)
            );

            String result = "";

            for (int i = 0; i < hash.length; i++) {
                result = result + String.format("%02x", hash[i]);
            }

            securityAnswer = result;

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {

        LibraryMemberBean member1 =
                new LibraryMemberBean("Priya Nair");

        System.out.println(member1.getMembershipId());

        LibraryMemberBean member2 =
                new LibraryMemberBean("LIB-8841", "Priya Nair");

        System.out.println(member2.getMembershipId());

        member1.setMembershipId("LIB-1001");
        member1.setMembershipId("LIB-2002");

        System.out.println(member1.getMembershipId());

        member1.setPremiumMember(true);

        System.out.println(member1.isPremiumMember());

        member1.setSecurityAnswer("blue");
    }
}