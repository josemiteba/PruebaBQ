package com.evernote.android.demo.util;

import com.evernote.edam.type.Note;

import java.util.Comparator;
import java.util.Date;

/**
 * Created by JoseMiguel on 30/01/2016.
 */
public class NoteDateComparator implements Comparator<Note> {

    @Override
    public int compare(Note lhs, Note rhs) {
        return new Date(lhs.getCreated()).compareTo(new Date(rhs.getCreated()));
    }
}
