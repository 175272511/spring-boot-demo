/*
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package elasticsearch;

import org.elasticsearch.client.node.NodeClient;
import org.elasticsearch.common.settings.Settings;

import java.util.UUID;

import static org.elasticsearch.node.NodeBuilder.nodeBuilder;

/**
 * @author Mohsin Husen
 */
public class Utils {

	public static NodeClient getNodeClient() {
		return (NodeClient) nodeBuilder().settings(Settings.builder()
				.put("http.enabled", "false")
				.put("path.data", "target/elasticsearchTestData")
				.put("path.home", "src/test/resources/test-home-dir"))
				.clusterName(UUID.randomUUID().toString()).local(true).node()
				.client();
	}
}
