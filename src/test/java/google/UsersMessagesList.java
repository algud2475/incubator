package google;

import java.util.List;

public class UsersMessagesList {
    private Integer resultSizeEstimate;
    private List<Messages> messages;

    public Integer getResultSizeEstimate() {
        return resultSizeEstimate;
    }

    public void setResultSizeEstimate(Integer resultSizeEstimate) {
        this.resultSizeEstimate = resultSizeEstimate;
    }

    public List<Messages> getMessages() {
        return messages;
    }

    public void setMessages(List<Messages> messages) {
        this.messages = messages;
    }
}
