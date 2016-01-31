package com.evernote.android.demo.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.evernote.android.demo.R;
import com.evernote.android.demo.fragment.note.NoteContainerFragment;
import com.evernote.android.demo.task.BaseTask;
import com.evernote.client.android.EvernoteSession;
import com.evernote.edam.type.LinkedNotebook;
import com.evernote.edam.type.Notebook;

import net.vrallev.android.task.TaskResult;
import com.evernote.android.demo.util.NoteTitleComparator;
import com.evernote.android.demo.util.NoteDateComparator;

import java.util.Collections;

/**
 * @author rwondratschek
 */
public class NotesActivity extends AppCompatActivity {

    private static final String KEY_NOTEBOOK = "KEY_NOTEBOOK";
    private static final String KEY_LINKED_NOTEBOOK = "KEY_LINKED_NOTEBOOK";

    public static Intent createIntent(Context context, Notebook notebook) {
        Intent intent = new Intent(context, NotesActivity.class);
        intent.putExtra(KEY_NOTEBOOK, notebook);
        return intent;
    }

    public static Intent createIntent(Context context, LinkedNotebook linkedNotebook) {
        Intent intent = new Intent(context, NotesActivity.class);
        intent.putExtra(KEY_LINKED_NOTEBOOK, linkedNotebook);
        return intent;
    }

    private Notebook mNotebook;
    private LinkedNotebook mLinkedNotebook;

    @SuppressWarnings("ConstantConditions")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        mNotebook = (Notebook) getIntent().getSerializableExtra(KEY_NOTEBOOK);
        mLinkedNotebook = (LinkedNotebook) getIntent().getSerializableExtra(KEY_LINKED_NOTEBOOK);

        Resources resources = getResources();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(resources.getColor(R.color.tb_text));

        setSupportActionBar(toolbar);

        if (!isTaskRoot()) {
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        if (mNotebook != null) {
            getSupportActionBar().setTitle(mNotebook.getName());
        } else {
            getSupportActionBar().setTitle(mLinkedNotebook.getShareName());
            new LoadNotebookNameTask(mLinkedNotebook).start(this, "notebookName");
        }

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, NoteContainerFragment.create(mNotebook, mLinkedNotebook))
                    .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.menu_item_sort_note_title:
                Toast toast1 =
                        Toast.makeText(getApplicationContext(),
                                "Titulo", Toast.LENGTH_SHORT);
                toast1.show();
                return true;
            case R.id.menu_item_sort_note_date:
                Toast toast2 =
                        Toast.makeText(getApplicationContext(),
                                "Fecha", Toast.LENGTH_SHORT);
                toast2.show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @TaskResult(id = "notebookName")
    public void onNotebookName(String name) {
        //noinspection ConstantConditions
        getSupportActionBar().setTitle(name);
    }

    private static final class LoadNotebookNameTask extends BaseTask<String> {

        private final LinkedNotebook mLinkedNotebook;

        private LoadNotebookNameTask(LinkedNotebook linkedNotebook) {
            super(String.class);
            mLinkedNotebook = linkedNotebook;
        }

        @Override
        protected String checkedExecute() throws Exception {
            return EvernoteSession.getInstance()
                    .getEvernoteClientFactory()
                    .getLinkedNotebookHelper(mLinkedNotebook)
                    .getCorrespondingNotebook()
                    .getName();
        }
    }
}
