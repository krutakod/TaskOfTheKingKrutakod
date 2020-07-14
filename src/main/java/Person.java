import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

/*
Данный класс описывает каждого жителя королевства.
Конечно, для переписи хватило бы и String,
но я понимаю, что по факту житель может быть более сложной сущностью: иметь не только имя.
Фантазировать не запрещали :)
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Person implements Comparable<Person> {

    private String name;

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(name, person.name);
    }

    @Override
    public int compareTo(Person o) {
        return this.getName().compareTo(o.getName());
    }
}
