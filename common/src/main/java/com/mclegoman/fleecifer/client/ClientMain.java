/*
    Fleecifer
    Contributor(s): dannytaylor
    Github: https://github.com/mclegoman/fleecifer
    License: GNU LGPLv3
*/

package com.mclegoman.fleecifer.client;

import com.mclegoman.fleecifer.client.model.Models;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClientMain {
	public static final String modId = "fleecifer";
	public static final Logger logger = LoggerFactory.getLogger(modId);
	public static void init() {
		Models.init();
	}
}
