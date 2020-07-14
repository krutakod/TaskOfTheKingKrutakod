import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UnluckyVassal {

    private final Pattern patternFullName = Pattern.compile("\\W*:");
    private final Pattern patternSubName = Pattern.compile(":\\W*$");


    public UnluckyVassal() {

    }

    @lombok.SneakyThrows
    public void printReportForKing(List<String> pollResults) {
        Tree<Person> tree = new Tree<>(new Person("Король"));

        for(String str : pollResults) {

            Matcher matcherFullName = patternFullName.matcher(str);

            Matcher matcherSubName = patternSubName.matcher(str);


            if (matcherFullName.find()) {
                Person topPerson = new Person(matcherFullName.group().replaceAll(":", ""));
                Top<Person> findPerson = tree.findTopByData(topPerson);
                Top<Person> readPerson = new Top<>(topPerson);
                if (findPerson == null) {
                    tree.add(readPerson);
                    if (matcherSubName.find()) {
                        List<String> listSubName = List.of(
                                matcherSubName
                                        .group()
                                        .replaceAll(": ", "")
                                        .split(", ")
                        );

                        for (String subName : listSubName) {
                            Person subPerson = new Person(subName);
                            Top<Person> findSubPerson = tree.findTopByData(subPerson);
                            if (findSubPerson == null) {
                                readPerson.add(new Top<>(subPerson));
                            } else {
                                tree.remove(findSubPerson);
                                readPerson.add(findSubPerson);//new Top<>(subPerson));
                            }
                        }

                    }
                } else {
                    if (matcherSubName.find()) {
                        List<String> listSubName = List.of(
                                matcherSubName
                                        .group()
                                        .replaceAll(": ", "")
                                        .split(", ")
                        );

                        for (String subName : listSubName) {
                            Person subPerson = new Person(subName);
                            Top<Person> findSubPerson = tree.findTopByData(subPerson);
                            if (findSubPerson == null) {
                                findPerson.add(new Top<>(subPerson));
                            } else {
                                tree.remove(findSubPerson);
                                findPerson.add(new Top<>(subPerson));
                            }
                        }
                    }
                }
            } else {
                Person topPerson = new Person(str);
                Top<Person> findPerson = tree.findTopByData(topPerson);
                Top<Person> readPerson = new Top<>(topPerson);

                if (findPerson == null) {
                    tree.add(readPerson);
                }
            }
        }

        tree.printCatalog();
    }

}
