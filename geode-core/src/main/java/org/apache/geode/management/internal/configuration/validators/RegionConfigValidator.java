/*
 * Licensed to the Apache Software Foundation (ASF) under one or more contributor license
 * agreements. See the NOTICE file distributed with this work for additional information regarding
 * copyright ownership. The ASF licenses this file to You under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance with the License. You may obtain a
 * copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package org.apache.geode.management.internal.configuration.validators;

import org.apache.geode.cache.configuration.CacheConfig;
import org.apache.geode.cache.configuration.CacheElement;
import org.apache.geode.cache.configuration.RegionConfig;
import org.apache.geode.internal.cache.RegionNameValidation;

public class RegionConfigValidator implements ConfigurationValidator<RegionConfig> {
  public static final String DEFAULT_REGION_TYPE = "PARTITION";

  @Override
  public void validate(RegionConfig config)
      throws IllegalArgumentException {
    // validate the name
    if (config.getName() == null) {
      throw new IllegalArgumentException("Name of the region has to be specified.");
    }

    RegionNameValidation.validate(config.getName());

    // validate the type
    if (config.getType() == null) {
      config.setType(DEFAULT_REGION_TYPE);
    }
  }

  @Override
  public boolean exists(RegionConfig config, CacheConfig existing) {
    return CacheElement.exists(existing.getRegions(), config.getId());
  }
}
