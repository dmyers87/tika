package org.apache.tika.parser.rtf;

/* Holds all state associated with the current RTF field. */
public class FieldState {

    public enum FieldInst {
        HYPERLINK, FORMCHECKBOX, FORMTEXT, UNKNOWN
    }

    public enum FieldGroups {
        fldinst, fldrslt, formfield, ffname
    }

    public enum FormFieldType {
        TEXT(0), CHECKBOX(1), LIST(2);

        int rtfType;

        FormFieldType(int val) {
            rtfType = val;
        }

        public static FormFieldType fromRtfType(int rtfValue) {
            FormFieldType ret = null;

            switch(rtfValue) {
                case 0: ret = TEXT; break;
                case 1: ret = CHECKBOX; break;
                case 2: ret = LIST; break;
            }

            return ret;
        }
    }

    private FieldInst inst;
    private FormFieldType type;
    private FieldGroups currentGroup;
    private boolean bufferText = false; // should the text found inside the group be buffered?
    private boolean checked; // checkbox status
    private String url; // hyperlink url
    private String result; // result text value
    private String name; // ffname text value

    public FieldState() {
    }

    public FieldInst getInst() {
        return inst;
    }

    public void setInst(FieldInst inst) {
        this.inst = inst;
    }

    public FormFieldType getType() {
        return type;
    }

    public void setType(FormFieldType type) {
        this.type = type;
    }

    public FieldGroups getCurrentGroup() {
        return currentGroup;
    }

    public void setCurrentGroup(FieldGroups currentGroup) {
        this.currentGroup = currentGroup;

        if (currentGroup != null) {
            switch(currentGroup) {
                case fldinst:
                    bufferText = true;
                    break;
                case formfield:
                    bufferText = true;
                    break;
                case ffname:
                    bufferText = true;
                    break;
                case fldrslt:
                    bufferText = true;
                    break;
                default:
                    bufferText = false;
            }
        } else {
            bufferText = false;
        }
    }

    public boolean isBufferText() {
        return bufferText;
    }

    public void setBufferText(boolean bufferText) {
        this.bufferText = bufferText;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
