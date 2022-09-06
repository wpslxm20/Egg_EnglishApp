package com.bicontest.egg.MainPages;

// 추천 단어 토글 열었을 때 보이는 연관어
public class ToggleWordsViewItem {
    private String mToggleWordEnglish;
    private String mToggleWordKorean;

    public ToggleWordsViewItem(String toggleWordEnglish, String toggleWordKorean) {
        this.mToggleWordEnglish = toggleWordEnglish;
        this.mToggleWordKorean = toggleWordKorean;
    }

    public String getToggleWordEnglish() {
        return mToggleWordEnglish;
    }

    public void setToggleWordEnglish(String toggleWordEnglish) {
        this.mToggleWordEnglish = toggleWordEnglish;
    }

    public String getToggleWordKorean() {
        return mToggleWordKorean;
    }

    public void setToggleWordKorean(String toggleWordKorean) {
        this.mToggleWordKorean = toggleWordKorean;
    }
}
