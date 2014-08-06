package com.eighth.airrent.dao;

import com.eighth.airrent.domain.Corp;
import com.eighth.airrent.domain.OpenPage;

public interface CorpDAO {
	String addCorp(Corp corp);

	OpenPage<Corp> getCorpPage(OpenPage openPage);

	Corp getCorpById(String corpId);

	String updateCorp(Corp corp);

	String deleteCorp(String corpId);
}
