package detail.acad.hassannaqvi.edu.aku.academicdetailing.JSON;

import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import detail.acad.hassannaqvi.edu.aku.academicdetailing.R;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.model.Result;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.util.Data;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.validation.validatorClass;
import io.blackbox_vision.datetimepickeredittext.view.DatePickerInputEditText;

public abstract class GeneratorClass {

    public static JSONObject formJSON;
    public static ArrayList<String> answers;
    public static int chkbxincr = 0;
    public static ArrayList<String> testAnswers;
    public static int incr = 0;
    public static ArrayList<String> chkboxAnswers;
    public static ArrayList<String> correctAnswers;
    public static double correct = 0;
    public static double wrong = 0;
    public static double total = 0;
    public static Result result;

    public static int ansIncrement = 1;

    public static JSONObject getContainerJSON(LinearLayout lv, boolean flag, String... convention) {

        if (flag){
            formJSON = new JSONObject();
            ansIncrement = 1;
        }



        try {

            for (int i = 0; i < lv.getChildCount(); i++) {
                View view = lv.getChildAt(i);

                String assig_id = convention.length > 0 ? convention[0] : "";


                if (view instanceof CardView) {
                    for (int j = 0; j < ((CardView) view).getChildCount(); j++) {
                        View view1 = ((CardView) view).getChildAt(j);
                        if (view1 instanceof LinearLayout) {
                            getContainerJSON((LinearLayout) view1, false, assig_id);
                        }
                    }
                } else if (view instanceof RadioGroup) {

                    RadioGroup rdp = (RadioGroup) view;
//                    assig_id += validatorClass.getIDComponent(rdp);
                    assig_id += (ansIncrement > 9 ? ansIncrement : "0" + ansIncrement);
                    int rdbID = rdp.getCheckedRadioButtonId();

                    if (rdbID != -1) {

                        for (byte j = 0; j < ((RadioGroup) view).getChildCount(); j++) {

                            if (rdbID == ((RadioGroup) view).getChildAt(j).getId()) {

                                RadioButton rdb = rdp.findViewById(((RadioGroup) view).getChildAt(j).getId());

                                formJSON.put(assig_id, getValues(validatorClass.getIDComponent(rdb)));

                                break;
                            }

                        }
                    } else {
                        formJSON.put(assig_id, "0");
                    }
                    ansIncrement++;
                } else if (view instanceof io.blackbox_vision.datetimepickeredittext.view.DatePickerInputEditText) {
//                    assig_id += validatorClass.getIDComponent(view);
                    assig_id += (ansIncrement > 9 ? ansIncrement : "0" + ansIncrement);
                    formJSON.put(assig_id, ((DatePickerInputEditText) view).getText().toString());
                    ansIncrement++;
                } else if (view instanceof EditText) {
//                    assig_id += validatorClass.getIDComponent(view);
                    assig_id += (ansIncrement > 9 ? ansIncrement : "0" + ansIncrement);
                    formJSON.put(assig_id, ((EditText) view).getText().toString());
                    ansIncrement++;
                } else if (view instanceof CheckBox) {
//                    assig_id += validatorClass.getIDComponent(view);
                    assig_id += (ansIncrement > 9 ? ansIncrement : "0" + ansIncrement);
                    if (((CheckBox) view).isChecked()) {
                        formJSON.put(assig_id, getValues(assig_id));
                    } else {
                        formJSON.put(assig_id, "0");
                    }
                    ansIncrement++;
                } else if (view instanceof Spinner) {
//                    assig_id += validatorClass.getIDComponent(view);
                    assig_id += (ansIncrement > 9 ? ansIncrement : "0" + ansIncrement);
                    if (((Spinner) view).getSelectedItemPosition() != 0) {
                        formJSON.put(assig_id, ((Spinner) view).getSelectedItem());
                    } else {
                        formJSON.put(assig_id, "");
                    }
                    ansIncrement++;
                }

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return formJSON;
    }

    public static void comparingResult(LinearLayout lv, boolean flag, ArrayList<String>... answers) {

        if (flag) {
            testAnswers = answers[0];
            incr = 0;
            chkbxincr = 0;
        }


        try {
            for (int i = 0; i < lv.getChildCount(); i++) {
                View view = lv.getChildAt(i);

                if (view instanceof CardView) {
                    for (int j = 0; j < ((CardView) view).getChildCount(); j++) {
                        View view1 = ((CardView) view).getChildAt(j);
                        if (view1 instanceof LinearLayout) {
                            comparingResult((LinearLayout) view1, false);
                        }
                    }
                } else if (view instanceof RadioGroup) {

                    RadioGroup rdg = (RadioGroup) view;
                    String tag1 = testAnswers.get(incr);
                    String tag2 = Data.pretestAnswers.get(incr);

                    for (int j = 0; j < ((RadioGroup) view).getChildCount(); j++) {
                        ((RadioGroup) view).getChildAt(j).setEnabled(false);
                        if (String.valueOf(rdg.getChildAt(j).getTag()).equals(tag2)) {
                            RadioButton rdb = rdg.findViewById(((RadioGroup) view).getChildAt(j).getId());
                            rdb.setButtonDrawable(R.drawable.cancel);
                            rdb.setCompoundDrawablesWithIntrinsicBounds(null, null, ContextCompat.getDrawable(rdg.getContext(), R.drawable.pretest), null);
                            rdb.setChecked(true);
                        }
                        if (String.valueOf(rdg.getChildAt(j).getTag()).equals(tag1)) {
                            RadioButton rdb = rdg.findViewById(((RadioGroup) view).getChildAt(j).getId());
                            rdb.setCompoundDrawablesWithIntrinsicBounds(null, null, ContextCompat.getDrawable(rdg.getContext(), R.drawable.correct), null);
                            rdb.setButtonDrawable(R.drawable.tick);
                            rdb.setChecked(true);
                        }


                    }


                } else if (view instanceof CheckBox) {

                    view.setEnabled(false);
                    String tag1 = Data.fanc_cb.get(chkbxincr);
                    String tag2 = Data.checkboxPreAnswers.get(chkbxincr);
                    if (String.valueOf(view.getTag()).equals(tag2)) {
//                        CheckBox rdb = chb.findViewById(chb.getId());
                        ((CheckBox) view).setButtonDrawable(R.drawable.cancel);
                        ((CheckBox) view).setCompoundDrawablesWithIntrinsicBounds(null, null, ContextCompat.getDrawable(view.getContext(), R.drawable.pretest), null);
                        ((CheckBox) view).setChecked(true);
                    }
                    if (String.valueOf(view.getTag()).equals(tag1)) {
//                        CheckBox rdb = chb.findViewById(chb.getId());
                        ((CheckBox) view).setCompoundDrawablesWithIntrinsicBounds(null, null, ContextCompat.getDrawable(view.getContext(), R.drawable.correct), null);
                        ((CheckBox) view).setButtonDrawable(R.drawable.tick);
                        ((CheckBox) view).setChecked(true);
                    }
                    chkbxincr++;


                }

            }
            incr++;


        } catch (Exception e) {

            e.printStackTrace();
        }

    }

    public static void comparingPostTestAndPretestResult(LinearLayout lv, boolean flag, ArrayList<String>... answers) {

        if (flag)
            if (answers.length != 0) {
                correctAnswers = answers[0];
                incr = 0;
                chkbxincr = 0;

            }

        try {
            for (int i = 0; i < lv.getChildCount(); i++) {
                View view = lv.getChildAt(i);

                if (view instanceof CardView) {
                    for (int j = 0; j < ((CardView) view).getChildCount(); j++) {
                        View view1 = ((CardView) view).getChildAt(j);
                        if (view1 instanceof LinearLayout) {
                            comparingPostTestAndPretestResult((LinearLayout) view1, false);
                        }
                    }
                } else if (view instanceof RadioGroup) {

                    RadioGroup rdg = (RadioGroup) view;
                    String tag1 = Data.pretestAnswers.get(incr);
                    String tag2 = Data.posttestAnswers.get(incr);
                    String tag3 = correctAnswers.get(incr);
                    for (int j = 0; j < ((RadioGroup) view).getChildCount(); j++) {
                        ((RadioGroup) view).getChildAt(j).setEnabled(false);
                        if (String.valueOf(rdg.getChildAt(j).getTag()).equals(tag2)) {
                            RadioButton rdb = rdg.findViewById(((RadioGroup) view).getChildAt(j).getId());
                            rdb.setButtonDrawable(R.drawable.cancel);
                            rdb.setCompoundDrawablesWithIntrinsicBounds(null, null, ContextCompat.getDrawable(rdg.getContext(), R.drawable.posttest), null);
                            rdb.setChecked(true);

                        }
                        if (String.valueOf(rdg.getChildAt(j).getTag()).equals(tag1)) {
                            RadioButton rdb = rdg.findViewById(((RadioGroup) view).getChildAt(j).getId());
                            rdb.setButtonDrawable(R.drawable.cancel);
                            rdb.setCompoundDrawablesWithIntrinsicBounds(null, null, ContextCompat.getDrawable(rdg.getContext(), R.drawable.pretest), null);
                            rdb.setChecked(true);


                        }
                        if (String.valueOf(rdg.getChildAt(j).getTag()).equals(tag3)) {
                            RadioButton rdb = rdg.findViewById(((RadioGroup) view).getChildAt(j).getId());
                            rdb.setButtonDrawable(R.drawable.tick);
                            rdb.setCompoundDrawablesWithIntrinsicBounds(null, null, ContextCompat.getDrawable(rdg.getContext(), R.drawable.correct), null);
                            rdb.setChecked(true);


                        }


                    }
                } else if (view instanceof CheckBox) {
                    view.setEnabled(false);
                    String tag1 = Data.fanc_cb.get(chkbxincr);
                    String tag2 = Data.checkboxPreAnswers.get(chkbxincr);
                    String tag3 = Data.checkboxPostAnswers.get(chkbxincr);
                    if (String.valueOf(view.getTag()).equals(tag2)) {
//                        CheckBox rdb = chb.findViewById(chb.getId());
                        ((CheckBox) view).setButtonDrawable(R.drawable.cancel);
                        ((CheckBox) view).setCompoundDrawablesWithIntrinsicBounds(null, null, ContextCompat.getDrawable(view.getContext(), R.drawable.pretest), null);
                        ((CheckBox) view).setChecked(true);
                    }
                    if (String.valueOf(view.getTag()).equals(tag1)) {
//                        CheckBox rdb = chb.findViewById(chb.getId());
                        ((CheckBox) view).setCompoundDrawablesWithIntrinsicBounds(null, null, ContextCompat.getDrawable(view.getContext(), R.drawable.correct), null);
                        ((CheckBox) view).setButtonDrawable(R.drawable.tick);
                        ((CheckBox) view).setChecked(true);
                    }
                    if (String.valueOf(view.getTag()).equals(tag3)) {
//                        CheckBox rdb = chb.findViewById(chb.getId());
                        ((CheckBox) view).setCompoundDrawablesWithIntrinsicBounds(null, null, ContextCompat.getDrawable(view.getContext(), R.drawable.posttest), null);
                        ((CheckBox) view).setButtonDrawable(R.drawable.cancel);
                        ((CheckBox) view).setChecked(true);
                    }
                    chkbxincr++;
                }


            }
            incr++;


        } catch (Exception e) {

            e.printStackTrace();
        }

    }

    public static Result getResults(String testType, ArrayList<String>... answers) {

        result = new Result();
        correct = 0;
        wrong = 0;
        correctAnswers = answers[0];
        total = 0;
        total = correctAnswers.size();
        ArrayList<String> testAnswers;

        if (testType.equals("pre")) {
            testAnswers = Data.pretestAnswers;
        } else {
            testAnswers = Data.posttestAnswers;
        }
        for (int i = 0; i < total; i++) {
            if (testAnswers.get(i).equals(correctAnswers.get(i))) {
                correct++;
            } else {
                wrong++;
            }
        }
        result.setCorrect(correct);
        result.setWrong(wrong);
        result.setTotal(total);
        double per = (correct / total) * 100;
        result.setPercentage(per);
        return result;

    }

    public static ArrayList<String> getAnswers(LinearLayout lv, boolean flag) {


        if (flag)
            answers = new ArrayList<>();


        try {
            for (int i = 0; i < lv.getChildCount(); i++) {
                View view = lv.getChildAt(i);

                if (view instanceof CardView) {
                    for (int j = 0; j < ((CardView) view).getChildCount(); j++) {
                        View view1 = ((CardView) view).getChildAt(j);
                        if (view1 instanceof LinearLayout) {
                            getAnswers((LinearLayout) view1, false);
                        }
                    }
                } else if (view instanceof RadioGroup) {

                    RadioGroup rdg = (RadioGroup) view;
                    int selectedId = rdg.getCheckedRadioButtonId();
                    if (selectedId != -1) {
                        for (int j = 0; j < ((RadioGroup) view).getChildCount(); j++) {
                            if (selectedId == ((RadioGroup) view).getChildAt(j).getId()) {
                                RadioButton rdb = rdg.findViewById(((RadioGroup) view).getChildAt(j).getId());
                                String tag = (String) rdb.getTag();
                                answers.add(tag);
                                break;
                            }

                        }
                    }


                }
            }
        } catch (Exception e) {

            e.printStackTrace();
        }


        return answers;

    }

    public static ArrayList<String> getChkboxAnswers(LinearLayout lv, boolean flag) {

        if (flag)
            chkboxAnswers = new ArrayList<>();
        try {
            for (int i = 0; i < lv.getChildCount(); i++) {
                View view = lv.getChildAt(i);

                if (view instanceof CardView) {
                    for (int j = 0; j < ((CardView) view).getChildCount(); j++) {
                        View view1 = ((CardView) view).getChildAt(j);
                        if (view1 instanceof LinearLayout) {
                            getChkboxAnswers((LinearLayout) view1, false);
                        }
                    }
                } else if (view instanceof CheckBox) {

                    if (((CheckBox) view).isChecked()) {
//                        CheckBox chkbox = ((CheckBox)view).findViewById(view.getId());
                        String tag = (String) view.getTag();
                        chkboxAnswers.add(tag);

                    } else {
                        chkboxAnswers.add("0");
                    }


                }
            }
        } catch (Exception e) {

            e.printStackTrace();
        }

        return chkboxAnswers;
    }


    private static String getValues(String nameconv) {

        if (nameconv.length() > 0) {

            String str = nameconv.substring(nameconv.length() - (nameconv.length() >= 2 ? 2 : 1)
            );

            char[] str_str = str.toLowerCase().toCharArray();

            if (str.charAt(str.length() - 1) <= '9') {
                return String.valueOf(Integer.parseInt(str));
            } else {

                String ascii_letters = "abcdefghijklmnopqrstuvwxyz";

                for (byte i = 0; i < ascii_letters.length(); i++) {
                    if (str_str[str.length() - 1] == ascii_letters.charAt(i)) {
                        return String.valueOf(i + 1);
                    }
                }

                return "0";

            }
        } else {
            return "";
        }
    }

}
