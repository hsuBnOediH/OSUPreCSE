package com.demo.colin.demo;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;


public class PreScheduleActivity extends Activity implements View.OnDragListener {
    /*CoursesTree which contains all the course information and user selection*/
    private CourseTree courseTree = new CourseTree();
    private HashSet<String> availCourse = new HashSet<>();

    ArrayList<ListView> listViewsArray = new ArrayList<>();
    ArrayList<LinearLayout> layoutsArray = new ArrayList<>();
    HashMap<ListView, AvaListAdapter> lvAdaMap = new HashMap<>();
    HashMap<LinearLayout, AvaListAdapter> layAdaMap = new HashMap<>();

    LinearLayout avaLayout;
    LinearLayout topLeftLayout;
    LinearLayout topRightLayout;
    LinearLayout botLeftLayout;
    LinearLayout botRightLayout;

    Stack<LinearLayout> enterStack = new Stack<>();

    TrackDragTable trackDragTable;
    String selectedCourse;

    private void findId() {


        ListView avaListView = findViewById(R.id.schedule_ava_list_view);
        ListView topLeftListView = findViewById(R.id.sch_sem_top_left_list_view);
        ListView topRightListView = findViewById(R.id.sch_sem_top_right_list_view);
        ListView botLeftListView = findViewById(R.id.sch_sem_bot_left_list_view);
        ListView botRightListView = findViewById(R.id.sch_sem_bot_right_list_view);

        AvaListAdapter avaListAdapter = new AvaListAdapter(availCourse, this);
        AvaListAdapter courseListTopLeft = new AvaListAdapter(new HashSet<String>(), this);
        AvaListAdapter courseListTopRight = new AvaListAdapter(new HashSet<String>(), this);
        AvaListAdapter courseListBotLeft = new AvaListAdapter(new HashSet<String>(), this);
        AvaListAdapter courseListBotRight = new AvaListAdapter(new HashSet<String>(), this);

        topRightListView.setAdapter(courseListTopRight);
        topLeftListView.setAdapter(courseListTopLeft);
        botRightListView.setAdapter(courseListBotRight);
        botLeftListView.setAdapter(courseListBotLeft);
        avaListView.setAdapter(avaListAdapter);

        avaLayout = findViewById(R.id.sch_ava_layout);
        topLeftLayout = findViewById(R.id.sch_sem_top_left_layout);
        topRightLayout = findViewById(R.id.sch_sem_top_right_layout);
        botLeftLayout = findViewById(R.id.sch_sem_bot_left_layout);
        botRightLayout = findViewById(R.id.sch_sem_bot_right_layout);

        listViewsArray.add(avaListView);
        listViewsArray.add(topLeftListView);
        listViewsArray.add(topRightListView);
        listViewsArray.add(botLeftListView);
        listViewsArray.add(botRightListView);

        layoutsArray.add(avaLayout);
        layoutsArray.add(topLeftLayout);
        layoutsArray.add(topRightLayout);
        layoutsArray.add(botLeftLayout);
        layoutsArray.add(botRightLayout);


        lvAdaMap.put(avaListView, avaListAdapter);
        lvAdaMap.put(topLeftListView, courseListTopLeft);
        lvAdaMap.put(topRightListView, courseListTopRight);
        lvAdaMap.put(botLeftListView, courseListBotLeft);
        lvAdaMap.put(botRightListView, courseListBotRight);

        layAdaMap.put(avaLayout, avaListAdapter);
        layAdaMap.put(topLeftLayout, courseListTopLeft);
        layAdaMap.put(topRightLayout, courseListTopRight);
        layAdaMap.put(botLeftLayout, courseListBotLeft);
        layAdaMap.put(botRightLayout, courseListBotRight);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_schedule);

        Intent intent = getIntent();
        HashSet<String> set = (HashSet<String>) getIntent().getSerializableExtra("Set");
        courseTree.firstMarkAndAddAll(set);
        availCourse = courseTree.getAvailCourse();

        trackDragTable = new TrackDragTable(this.courseTree);


        findId();


        implementEvents();

    }

    private void setItemLongClickListener(ListView lv) {
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View view,
                                           int pos, long id) {
                // Create a new ClipData.
                // This is done in two steps to provide clarity. The convenience method
                // ClipData.newPlainText() can create a plain text ClipData in one step.

                // Create a new ClipData.Item from the ImageView object's tag
                ClipData.Item item = new ClipData.Item((CharSequence) view.getTag());

                // Create a new ClipData using the tag as a label, the plain text MIME type, and
                // the already-created item. This will create a new ClipDescription object within the
                // ClipData, and set its MIME type entry to "text/plain"
                String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};

                ClipData data = new ClipData(view.getTag().toString(), mimeTypes, item);

                // Instantiates the drag shadow builder.
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);

                // Starts the drag
                view.startDrag(data//data to be dragged
                        , shadowBuilder //drag shadow
                        , view//local data about the drag and drop operation
                        , 0//no needed flags
                );

                //Set view visibility to INVISIBLE as we are going to drag the view
                return true;
            }
        });
    }


    //Implement long click and drag listener
    private void implementEvents() {

        for (ListView lv : listViewsArray) {
            setItemLongClickListener(lv);
        }

        for (LinearLayout linearLayout : layoutsArray) {
            linearLayout.setOnDragListener(this);
        }
    }


    // This is the method that the system calls when it dispatches a drag event to the
    // listener.
    @Override
    public boolean onDrag(View view, DragEvent event) {

        // Defines a variable to store the action type for the incoming event
        int action = event.getAction();
        // Handles each of the expected events
        switch (action) {
            case DragEvent.ACTION_DRAG_STARTED:
                // Determines if this View can accept the dragged data
                // if you want to apply color when drag started to your view you can uncomment below lines
                // to give any color tint to the View to indicate that it can accept
                // data.
                //  view.getBackground().setColorFilter(Color.BLUE, PorterDuff.Mode.SRC_IN);//set background color to your view
                // Invalidate the view to force a redraw in the new tint
                //  view.invalidate();
                // returns true to indicate that the View can accept the dragged data.
                return event.getClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN);

            // Returns false. During the current drag and drop operation, this View will
            // not receive events again until ACTION_DRAG_ENDED is sent.

            case DragEvent.ACTION_DRAG_ENTERED:
                // Applies a YELLOW or any color tint to the View, when the dragged view entered into drag acceptable view
                // Return true; the return value is ignored.
                LinearLayout curLayout = (LinearLayout) view;
                int layoutPosition = layoutsArray.indexOf(curLayout);

                if (enterStack.empty()) {
                    View itemInList = (View) event.getLocalState();
                    TextView clickedListView = itemInList.findViewById(R.id.sch_ava_item_text);
                    selectedCourse = clickedListView.getText().toString();
                    view.getBackground().setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_IN);
                    enterStack.push(curLayout);
                } else {
                    if (curLayout.equals(avaLayout)) {
                        view.getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_IN);
                    } else {
                        if (trackDragTable.isDoableSem(selectedCourse, layoutPosition)) {
                            if (trackDragTable.isSuitableSem(selectedCourse, layoutPosition)) {
                                view.getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_IN);
                            } else {
                                view.getBackground().setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_IN);
                            }
                        } else {
                            view.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);
                        }
                    }
                }


                // Invalidate the view to force a redraw in the new tint
                view.invalidate();

                return true;
            case DragEvent.ACTION_DRAG_LOCATION:
                // Ignore the event
                return true;
            case DragEvent.ACTION_DRAG_EXITED:
                // Re-sets the color tint to blue, if you had set the BLUE color or any color in ACTION_DRAG_STARTED. Returns true; the return value is ignored.

                //  view.getBackground().setColorFilter(Color.BLUE, PorterDuff.Mode.SRC_IN);

                //If u had not provided any color in ACTION_DRAG_STARTED then clear color filter.
                view.getBackground().clearColorFilter();
                // Invalidate the view to force a redraw in the new tint
                view.invalidate();

                return true;
            case DragEvent.ACTION_DROP:
                enterStack = new Stack<>();
                // Gets the item containing the dragged data
                ClipData.Item item = event.getClipData().getItemAt(0);

                // Gets the text data from the item.
                String dragData = item.getText().toString();


                // Turns off any color tints
                view.getBackground().clearColorFilter();

                // Invalidates the view to force a redraw
                view.invalidate();

                View itemInList = (View) event.getLocalState();

                ListView oldListView = (ListView) itemInList.getParent();

                LinearLayout newLinearLayout = (LinearLayout) view;
                AvaListAdapter removeItem = lvAdaMap.get(oldListView);
                AvaListAdapter addItem = layAdaMap.get(newLinearLayout);


                TextView textView = itemInList.findViewById(R.id.sch_ava_item_text);
                String courseName = textView.getText().toString();
                int curLayOutID = this.layoutsArray.indexOf(newLinearLayout);

                if (curLayOutID == 0) {
                    removeItem.remove(courseName);
                    ArrayList<String> subCourses = this.courseTree.getAllCourse().get(selectedCourse).getSub();
                    ArrayList<AvaListAdapter> allAdapters = new ArrayList<>(layAdaMap.values());
                    // Update all the layouts that contains the subCourses of the selected course;
                    for (AvaListAdapter adapter : allAdapters) {
                        adapter.removeAll(this.courseTree.getDeepSub(selectedCourse));
                    }
                    // Update the courTree including the flagTree and FinishedCourses
                    this.courseTree.changeCourseState(selectedCourse);
                    this.courseTree.addAvailableCourse(selectedCourse);
                    this.courseTree.undo(subCourses);
                    addItem.add(selectedCourse);
                    for (String subCourse : subCourses) {
                        if (!(this.courseTree.getFlagCourse().get(subCourse) == State.AVAILABLE)) {
                            addItem.remove(subCourse);
                        }
                    }
                   trackDragTable.deleteCourseInTable(selectedCourse,courseTree);
                    // TODO 更新追踪列表的layout ID

                } else {
                    if (this.trackDragTable.isDoableSem(courseName, curLayOutID)) {
                        removeItem.remove(courseName);
                        addItem.add(courseName);
                        if (!newLinearLayout.equals(avaLayout)) {
                            trackDragTable.addCourse(courseName, layoutsArray.indexOf(newLinearLayout));
                        }
                        // Update the available course
                        if (oldListView == findViewById(R.id.schedule_ava_list_view) && newLinearLayout != findViewById(R.id.sch_ava_layout)) {
                            HashSet<String> newAvailableCourse = courseTree.updateFinishedCourse(courseName);
                            lvAdaMap.get(oldListView).updateAvailable(newAvailableCourse);
                        }
                        this.trackDragTable.updateAllPreMax(courseTree.getAllCourse().get(selectedCourse).getPre());
                    } else {
                        Toast.makeText(this, "You can not take this course for this semester!", Toast.LENGTH_LONG).show();
                    }
                }

                // Returns true. DragEvent.getResult() will return true.
                return true;
            case DragEvent.ACTION_DRAG_ENDED:
                // Turns off any color tinting
                view.getBackground().clearColorFilter();

                // Invalidates the view to force a redraw
                view.invalidate();

                // Does a getResult(), and displays what happened.
                event.getResult();// Toast.makeText(this, "The drop was handled.", Toast.LENGTH_SHORT).show();


                // returns true; the value is ignored.
                return true;

            // An unknown action type was received.
            default:
                break;
        }
        return false;
    }


}
