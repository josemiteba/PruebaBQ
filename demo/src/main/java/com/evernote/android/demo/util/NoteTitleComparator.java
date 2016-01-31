package com.evernote.android.demo.util;

import com.evernote.edam.type.Note;

import java.util.Comparator;

/**
 * Created by JoseMiguel on 30/01/2016.
 */
public class NoteTitleComparator implements Comparator<Note> {

    @Override
    public int compare(Note lhs, Note rhs) {
        return lhs.getTitle().compareTo(rhs.getTitle());
    }
}