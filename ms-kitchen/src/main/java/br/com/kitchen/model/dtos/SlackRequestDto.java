package br.com.kitchen.model.dtos;

import java.io.Serializable;

public class SlackRequestDto implements Serializable {

    private String channel;
    private String username;
    private String text;
    private String icon_emoji;

    public SlackRequestDto(){}
    
    public SlackRequestDto(String username, String channel, String iconEmoji, String text) {
        this.setUsername(username);
        this.setChannel(channel);
        this.setIcon_emoji(iconEmoji);
        this.setText(text);
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getIcon_emoji() {
        return icon_emoji;
    }

    public void setIcon_emoji(String icon_emoji) {
        this.icon_emoji = icon_emoji;
    }
}
