public class School {
        public static void main(String[] args) {
            String section = "10th, by school";

            if(section.endsWith("by school")) {
                if(section.contains("9th")) {
                    System.out.println("Your standard is 9th");
                }else if(section.contains("8th")) {
                    System.out.println("Your standard is 8th");
                }else {
                    System.out.println(section.split(",")[0]);
                }
            }else {
                System.out.println("You are not living in by school");
            }
        }
    }
