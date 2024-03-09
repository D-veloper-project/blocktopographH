package io.vn.nguyenduck.blocktopograph.nbt.tag;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import io.vn.nguyenduck.blocktopograph.nbt.Type;
import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringJoiner;
import java.util.stream.Stream;

import static io.vn.nguyenduck.blocktopograph.Logger.LOGGER;
import static io.vn.nguyenduck.blocktopograph.nbt.Type.LIST;

public class ListTag implements Tag {
    private final String name;
    private final ArrayList<Tag> value;
    private final Type childNbtType;

    public ListTag(String name, ArrayList<Tag> value, Type childNbtType) {
        this.name = name != null ? name : "";
        this.value = value;
        this.childNbtType = childNbtType;
    }

    @NonNull
    @Override
    public Type getType() {
        return LIST;
    }

    @NonNull
    @Override
    public String getName() {
        return name;
    }

    @NonNull
    @Override
    public ArrayList<Tag> getValue() {
        return value;
    }

    @NonNull
    public Type getChildNbtType() {
        return childNbtType;
    }

    @NonNull
    @Override
    public String toString() {
        ArrayList<String> a = new ArrayList<>();
        for (Tag t : value) a.add(t.toString());
        boolean isGreaterThan100 = (String.valueOf(a).length() + (a.size() - 1) * 2) > 100;
        StringJoiner valueString = new StringJoiner(isGreaterThan100 ? ",\n" : ", ");
        a.forEach(valueString::add);
        return (name.isEmpty() ? "[" : ("\"" + name + "\": [")) +
                ((isGreaterThan100 ? "\n" : "") + valueString).replace("\n", "\n\t") +
                (isGreaterThan100 ? "\n]" : "]");
    }
}