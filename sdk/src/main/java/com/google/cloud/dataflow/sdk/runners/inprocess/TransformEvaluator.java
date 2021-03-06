/*
 * Copyright (C) 2015 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.google.cloud.dataflow.sdk.runners.inprocess;

import com.google.cloud.dataflow.sdk.runners.inprocess.InProcessPipelineRunner.CommittedBundle;
import com.google.cloud.dataflow.sdk.util.WindowedValue;

/**
 * An evaluator of a specific application of a transform. Will be used for at least one
 * {@link CommittedBundle}.
 *
 * @param <InputT> the type of elements that will be passed to {@link #processElement}
 */
interface TransformEvaluator<InputT> {
  /**
   * Process an element in the input {@link CommittedBundle}.
   *
   * @param element the element to process
   */
  void processElement(WindowedValue<InputT> element) throws Exception;

  /**
   * Finish processing the bundle of this {@link TransformEvaluator}.
   *
   * <p>After {@link #finishBundle()} is called, the {@link TransformEvaluator} will not be reused,
   * and no more elements will be processed.
   *
   * @return an {@link InProcessTransformResult} containing the results of this bundle evaluation.
   */
  InProcessTransformResult finishBundle() throws Exception;
}

