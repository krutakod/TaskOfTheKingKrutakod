import lombok.SneakyThrows;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TreeTest {

    private final static Tree<Person> KING = new Tree<>(new Person("Король"));

    @SneakyThrows
    @BeforeClass
    public static void createData(){
        Top<Person> person1 = new Top<>(new Person("Иван"));
        KING.add(person1);

        Top<Person> person2 = new Top<>(new Person("Василий"));
        person1.add(person2);

        Top<Person> person3 = new Top<>(new Person("Егор"));
        person2.add(person3);

        Top<Person> person4 = new Top<>(new Person("Мария"));
        KING.add(person4);

        Top<Person> person5 = new Top<>(new Person("Оля"));
        person4.add(person5);

    }

    @SneakyThrows
    @Test
    public void testExAddTop(){
        Assert.assertThrows(TreeException.class,
                ()-> KING.add(new Top<>(new Person("Иван"))));
    }

    @SneakyThrows
    @Test
    public void testFindTopByData(){
        Top<Person> person3 = new Top<>(new Person("Егор"));

        Assert.assertEquals("Егор",
                KING.findTopByData(new Person("Егор")).getData().getName());
    }

    @Test
    public void testRemoveTop(){

        Top<Person> findPerson = KING.findTopByData(new Person("Мария"));
        KING.remove(findPerson);

        Assert.assertNull(KING.findTopByData(new Person("Мария")));
        Assert.assertNull(KING.findTopByData(new Person("Оля")));
    }
}
