package net.gimenete.prototype;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.text.style.BackgroundColorSpan;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.URLSpan;
import android.text.style.UnderlineSpan;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class ItemListActivity extends AppCompatActivity {

    private ListView mListView;

    private ArrayAdapter<String> mAdapter;

    private ArrayList<String> messages;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);
        final EditText messageBox = (EditText) findViewById(R.id.etMessage);
        final Button btn = (Button) findViewById(R.id.btSend);
        mListView = (ListView) findViewById(R.id.lvChat);
        mListView.setDividerHeight(0);
        messages = new ArrayList<>();
        messages.add("Large");
        messages.add("Bold");
        messages.add("Underlined");
        messages.add("Italic");
        messages.add("Strikethrough");
        messages.add("Colored");
        messages.add("Highlighted");
        messages.add("Url");
        messages.add("Clickable");
        messages.add(
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam eleifend ligula "
                +
                        "in odio rhoncus, nec facilisis nulla ornare. Cras ultrices.");
        messages.add(
                "Lorem ipsum dolor sit amet, consectetur adipiscingelit.Morbiidultricieseros,"
                        +
                        "nonauctorlorem.Nullaquiselementumquam."
                        +
                        " Fusce tincidunt vel magna ut congue.");
        messages.add(
                "Lorem ipsum dolor sit amet, consectetur adipiscingelit.Morbiidultricieseros,"
                        +
                        "nonauctorlorem.Nullaquiselementumquam."
                        +
                        " Fusce tincidunt vel magna ut congue. Etiam a nisi vel ante tristique"
                        +
                        " rhoncus et vel ligula.");
        mAdapter = new MessagesAdapter(this,
                R.layout.message_item,
                messages);
        mListView.setAdapter(mAdapter);

        messageBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence txt, int start, int before, int count) {
                int numberOfLines = messageBox.getLineCount();
                if (numberOfLines >= 1 && numberOfLines < 5) {
                    //Update the message box layout when a new line of text is added
                    RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT);
                    params.addRule(RelativeLayout.LEFT_OF, R.id.btSend);
                    float d = getResources().getDisplayMetrics().density;
                    int margin = (int) (10 * d);
                    params.setMargins(margin, 0, 0, margin);
                    params.addRule(RelativeLayout.CENTER_VERTICAL);
                    params.addRule(RelativeLayout.CENTER_HORIZONTAL);
                    messageBox.setLayoutParams(params);
                    messageBox.setLines(numberOfLines);
                    messageBox.requestLayout();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (messageBox.getText().length() > 0) {
                    messages.add(messageBox.getText().toString());
                    messageBox.getText().clear();
                    mAdapter.notifyDataSetChanged();
                }
            }
        });

    }

    private class MessagesAdapter extends ArrayAdapter<String> {

        public MessagesAdapter(Context context, int resource, ArrayList<String> objects) {
            super(context, resource, objects);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            SpannableString message = new SpannableString(getItem(position));
            
            ////////////////////////////////////////////////////////////////
            //////// This is a demos of different styles            ////////
            ////////////////////////////////////////////////////////////////

            /* TODO You have to add your parser engine here instead of my demo */

            switch (position) {
                case 0:
                    //Text with large font
                    message.setSpan(new RelativeSizeSpan(2f), 0, 5, 0);
                    break;
                case 1:
                    //Text with BOLD font
                    message.setSpan(new StyleSpan(Typeface.BOLD), 0, 4, 0);
                    break;
                case 2:
                    //Text with underline
                    message.setSpan(new UnderlineSpan(), 0, 10, 0);
                    break;
                case 3:
                    //Text with italic font
                    message.setSpan(new StyleSpan(Typeface.ITALIC), 0, 6, 0);
                    break;
                case 4:
                    //Text with Strikethrough
                    message.setSpan(new StrikethroughSpan(), 0, 13, 0);
                    break;
                case 5:
                    //Text with green color font
                    message.setSpan(new ForegroundColorSpan(Color.GREEN), 0, 7, 0);
                    break;
                case 6:
                    //Text with highlighted cyan color
                    message.setSpan(new BackgroundColorSpan(Color.CYAN), 0, 11, 0);
                    break;
                case 7:
                    //Text with link
                    message.setSpan(new URLSpan("http://www.google.com"), 0, 3, 0);
                    break;
                case 8:
                    ClickableSpan clickableSpan = new ClickableSpan() {

                        @Override
                        public void onClick(View widget) {
                            Toast.makeText(getContext(), "Lorem ipsum!", Toast.LENGTH_SHORT).show();
                        }
                    };
                    //Text with clickable listener
                    message.setSpan(clickableSpan, 0, 9, 0);
                    break;
                case 9:
                    //Text with BOLD font in two lines
                    message.setSpan(new StyleSpan(Typeface.BOLD), 49, 95, 0);
                    break;
                case 10:
                    //Text with BOLD font in a sigle word in two lines
                    message.setSpan(new StyleSpan(Typeface.BOLD), 49, 95, 0);
                    break;
                case 11:
                    //Text with several styles
                    message.setSpan(new StyleSpan(Typeface.BOLD), 55, 91, 0);
                    message.setSpan(new URLSpan("http://www.google.com"), 0, 5, 0);
                    message.setSpan(new UnderlineSpan(), 22, 54, 0);
                    message.setSpan(new BackgroundColorSpan(Color.CYAN), 22, 54, 0);
                    message.setSpan(new ForegroundColorSpan(Color.GREEN), 6, 17, 0);
                    message.setSpan(new StrikethroughSpan(), 152, 164, 0);
                    break;

                default:
                    break;
            }

            TextView messageText = new TextView(getContext());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            messageText.setLayoutParams(params);

            /* TODO add rule to put the message in the right side (left/right) */
            messageText.setGravity((position % 2 == 0) ? Gravity.LEFT : Gravity.RIGHT);
            if (position % 2 == 0) {
                messageText.setPadding(10, 10, 200, 10);
            } else {
                messageText.setPadding(200, 10, 10, 10);
            }

            messageText.setMovementMethod(LinkMovementMethod.getInstance());
            messageText.setText(message);
            convertView = messageText;

            return convertView;
        }
    }
}
