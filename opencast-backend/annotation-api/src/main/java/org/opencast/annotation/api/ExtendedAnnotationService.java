/**
 *  Copyright 2012, Entwine GmbH, Switzerland
 *  Licensed under the Educational Community License, Version 2.0
 *  (the "License"); you may not use this file except in compliance
 *  with the License. You may obtain a copy of the License at
 *
 *  http://www.osedu.org/licenses/ECL-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an "AS IS"
 *  BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 *  or implied. See the License for the specific language governing
 *  permissions and limitations under the License.
 *
 */
package org.opencast.annotation.api;

import org.opencastproject.mediapackage.MediaPackage;
import org.opencastproject.util.data.Option;

import java.util.Map;
import java.util.stream.Stream;

public interface ExtendedAnnotationService {

  /**
   * Create a new user.
   *
   * @param extId
   *          the user's external id
   * @param nickname
   *          the user's nickname
   * @param email
   *          the user's email
   * @param resource
   *          the base {@link Resource}
   * @return the created user
   * @throws ExtendedAnnotationException
   *           if an error occurs while storing/retrieving from persistence storage
   */
  User createUser(String extId, String nickname, Option<String> email, Resource resource)
          throws ExtendedAnnotationException;

  /**
   * Update an existing user.
   *
   * @param u
   *          the user to update
   * @throws ExtendedAnnotationException
   *           if an error occurs while storing/retrieving from persistence storage
   */
  void updateUser(User u) throws ExtendedAnnotationException;

  /**
   * Delete a user.
   *
   * @param u
   *          the user
   * @return true if the user existed and could be successfully deleted.
   * @throws ExtendedAnnotationException
   *           if an error occurs while storing/retrieving from persistence storage
   */
  boolean deleteUser(User u) throws ExtendedAnnotationException;

  /**
   * Clear all annotation tables
   *
   * @return true if all tables could be successfully cleared.
   * @throws ExtendedAnnotationException
   *           if an error occurs while deleting from persistence storage
   */
  boolean clearDatabase() throws ExtendedAnnotationException;

  /**
   * Get a user by id.
   *
   * @param id
   *          the user's internal id
   * @return the requested user
   * @throws ExtendedAnnotationException
   *           if an error occurs while storing/retrieving from persistence storage
   */
  Option<User> getUser(long id) throws ExtendedAnnotationException;

  /**
   * Get a user by his external id, which is the id he has in the surrounding video portal.
   *
   * @param id
   *          the user's external id
   * @return the user
   * @throws ExtendedAnnotationException
   *           if an error occurs while storing/retrieving from persistence storage
   */
  Option<User> getUserByExtId(String id) throws ExtendedAnnotationException;

  /**
   * Create a new video.
   *
   * @param extId
   *          the video's id in the surrounding video portal.
   * @param resource
   *          the base {@link Resource}
   * @return the created video
   * @throws ExtendedAnnotationException
   *           if an error occurs while storing/retrieving from persistence storage
   */
  Video createVideo(String extId, Resource resource) throws ExtendedAnnotationException;

  /**
   * Update a video.
   *
   * @param v
   *          the video to update
   * @throws ExtendedAnnotationException
   *           if an error occurs while storing/retrieving from persistence storage
   */
  void updateVideo(Video v) throws ExtendedAnnotationException;

  /**
   * Delete a video.
   *
   * @param v
   *          the video
   * @return true if the video existed and could be successfully deleted.
   * @throws ExtendedAnnotationException
   *           if an error occurs while storing/retrieving from persistence storage
   */
  boolean deleteVideo(Video v) throws ExtendedAnnotationException;

  /**
   * Get a video by id.
   *
   * @param id
   *          the video id
   * @return the video
   * @throws ExtendedAnnotationException
   *           if an error occurs while storing/retrieving from persistence storage
   */
  Option<Video> getVideo(long id) throws ExtendedAnnotationException;

  /**
   * Get a video by its external id, which is the id it has in to surrounding video portal.
   *
   * @param id
   *          videos external id
   * @return the video
   * @throws ExtendedAnnotationException
   *           if an error occurs while storing/retrieving from persistence storage
   */
  Option<Video> getVideoByExtId(String id) throws ExtendedAnnotationException;

  /**
   * Create a new track in a given video.
   *
   * @param videoId
   *          the video id
   * @param name
   *          the name
   * @param description
   *          the description
   * @param settings
   *          the settings
   * @param resource
   *          the base {@link Resource}
   * @return the created track
   * @throws ExtendedAnnotationException
   *           if an error occurs while storing/retrieving from persistence storage
   */
  Track createTrack(long videoId, String name, Option<String> description, Option<String> settings,
          Resource resource) throws ExtendedAnnotationException;

  /**
   * Update a track of a video.
   *
   * @param track
   *          the track to update
   * @throws ExtendedAnnotationException
   *           if an error occurs while storing/retrieving from persistence storage
   */
  void updateTrack(Track track) throws ExtendedAnnotationException;

  /**
   * Delete a track.
   *
   * @param track
   *          the track to delete
   * @return true if the track existed and could be successfully deleted.
   * @throws ExtendedAnnotationException
   *           if an error occurs while storing/retrieving from persistence storage
   */
  boolean deleteTrack(Track track) throws ExtendedAnnotationException;

  /**
   * Get a track.
   *
   * @param id
   *          the track id
   * @return the requested track
   * @throws ExtendedAnnotationException
   *           if an error occurs while storing/retrieving from persistence storage
   */
  Option<Track> getTrack(long id) throws ExtendedAnnotationException;

  /**
   * Get all tracks from a video.
   *
   * @param videoId the video id
   * @return the track list or an empty list if no track has been found
   * @throws ExtendedAnnotationException if an error occurs while storing/retrieving from persistence storage
   */
  Stream<Track> getTracks(long videoId) throws ExtendedAnnotationException;

  /**
   * Annotate a track of a video.
   *
   * @param trackId
   *          the track id
   * @param start
   *          the annotation entry timepoint in seconds
   * @param duration
   *          the duration of the annotation in seconds
   * @param content
   *          the content of the annotation
   * @param createdFromQuestionnaire
   *          the questionnaire ID of the annotation
   * @param settings
   *          the settings
   * @param resource
   *          the base {@link Resource}
   * @return the created annotation
   * @throws ExtendedAnnotationException
   *           if an error occurs while storing/retrieving from persistence storage
   */
  Annotation createAnnotation(long trackId, double start, Option<Double> duration, String content,
          long createdFromQuestionnaire, Option<String> settings, Resource resource)
          throws ExtendedAnnotationException;

  /**
   * Create an annotation with a certain annotation.
   *
   * @param annotation
   *          the annotation to store
   * @return the stored annotation
   * @throws ExtendedAnnotationException
   *           if an error occurs while storing/retrieving from persistence storage
   */
  Annotation createAnnotation(Annotation annotation) throws ExtendedAnnotationException;

  /**
   * Update an annotation.
   *
   * @param annotation
   *          the annotation to update
   * @throws ExtendedAnnotationException
   *           if an error occurs while storing/retrieving from persistence storage
   */
  void updateAnnotation(Annotation annotation) throws ExtendedAnnotationException;

  /**
   * Delete an annotation.
   *
   * @param annotation
   *          the annotation to delete
   * @return true if the annotation existed and could be successfully deleted.
   * @throws ExtendedAnnotationException
   *           if an error occurs while storing/retrieving from persistence storage
   */
  boolean deleteAnnotation(Annotation annotation) throws ExtendedAnnotationException;

  /**
   * Get an annotation.
   *
   * @param id
   *          the annotation id
   * @return the requested annotation
   * @throws ExtendedAnnotationException
   *           if an error occurs while storing/retrieving from persistence storage
   */
  Option<Annotation> getAnnotation(long id) throws ExtendedAnnotationException;

  /**
   * Get annotations of a track.
   *
   * @param trackId the track id
   * @return the annotation list or an empty list if no annotation has been found
   * @throws ExtendedAnnotationException if an error occurs while storing/retrieving from persistence storage
   */
  Stream<Annotation> getAnnotations(long trackId) throws ExtendedAnnotationException;

  /**
   * Create a scale
   *
   * @param videoId
   *          the video id or none if it is a template scale
   * @param name
   *          the scale name
   * @param description
   *          the scale description
   * @return the created scale
   * @throws ExtendedAnnotationException
   *           if an error occurs while storing/retrieving from persistence storage
   */
  Scale createScale(long videoId, String name, Option<String> description, Resource resource)
          throws ExtendedAnnotationException;

  /**
   * Creates a copy of a templates scale
   *
   * @param videoId
   *          the video id
   * @param templateScale
   *          the template scale to copy
   * @param resource
   *          the resource
   * @return the created scale
   * @throws ExtendedAnnotationException
   *           if an error occurs while storing/retrieving from persistence storages
   */
  Scale createScaleFromTemplate(long videoId, long templateScale, Resource resource) throws ExtendedAnnotationException;

  /**
   * Get a scale by id.
   *
   * @param id
   *          the scale id
   * @param includeDeleted
   *          if <code>true</code> it will find also deleted scales
   * @return the scale
   * @throws ExtendedAnnotationException
   *           if an error occurs while storing/retrieving from persistence storage
   */
  Option<Scale> getScale(long id, boolean includeDeleted) throws ExtendedAnnotationException;

  /**
   * Get all scales from a video.
   *
   * @param videoId the video id
   * @return the scale list or an empty list if no scale has been found
   * @throws ExtendedAnnotationException if an error occurs while storing/retrieving from persistence storage
   */
  Stream<Scale> getScales(long videoId) throws ExtendedAnnotationException;

  /**
   * Update a scale.
   *
   * @param scale
   *          the scale to update
   * @throws ExtendedAnnotationException
   *           if an error occurs while storing/retrieving from persistence storage
   */
  void updateScale(Scale scale) throws ExtendedAnnotationException;

  /**
   * Delete a scale.
   *
   * @param scale
   *          the scale to delete
   * @return a representation of the deleted scale
   * @throws ExtendedAnnotationException
   *           if an error occurs while storing/retrieving from persistence storage
   */
  Scale deleteScale(Scale scale) throws ExtendedAnnotationException;

  /**
   * Create a scale value
   *
   * @param name
   *          the scale name
   * @param value
   *          the scale value
   * @param order
   *          the scale order
   * @param scaleId
   *          the scaleId
   * @param resource
   *          the resource
   * @return the created scale value
   * @throws ExtendedAnnotationException
   *           if an error occurs while storing/retrieving from persistence storage
   */
  ScaleValue createScaleValue(long scaleId, String name, double value, int order, Resource resource)
          throws ExtendedAnnotationException;

  /**
   * Get a scale value by id.
   *
   * @param id
   *          the scale value id
   * @param includeDeleted
   *          if <code>true</code> it will find also deleted scale values
   * @return the scale value
   * @throws ExtendedAnnotationException
   *           if an error occurs while storing/retrieving from persistence storage
   */
  Option<ScaleValue> getScaleValue(long id, boolean includeDeleted) throws ExtendedAnnotationException;

  /**
   * Get all scale values from a scale.
   *
   * @param scaleId the scale id
   * @return the scale value list or an empty list if no scale values has been found
   * @throws ExtendedAnnotationException if an error occurs while storing/retrieving from persistence storage
   */
  Stream<ScaleValue> getScaleValues(long scaleId) throws ExtendedAnnotationException;

  /**
   * Update a scale value
   *
   * @param scaleValue
   *          the scale value to update
   * @throws ExtendedAnnotationException
   *           if an error occurs while storing/retrieving from persistence storage
   */
  void updateScaleValue(ScaleValue scaleValue) throws ExtendedAnnotationException;

  /**
   * Delete a scale value
   *
   * @param scaleValue
   *          the scale value to delete
   * @return a representation of the deleted scale value
   * @throws ExtendedAnnotationException
   *           if an error occurs while storing/retrieving from persistence storage
   */
  ScaleValue deleteScaleValue(ScaleValue scaleValue) throws ExtendedAnnotationException;

  /**
   * Create a new questionnaire in a given video.
   *
   * @param videoId
   *          the video id
   * @param title
   *          the title
   * @param content
   *          the content
   * @param settings
   *          the settings
   * @param resource
   *          the base {@link Resource}
   * @return the created questionnaire
   * @throws ExtendedAnnotationException
   *           if an error occurs while storing/retrieving from persistence storage
   */
  Questionnaire createQuestionnaire(long videoId, String title, String content,
         Option<String> settings, Resource resource) throws ExtendedAnnotationException;

  /**
   * Creates a questionnaire.
   *
   * @param templateQuestionnaireId
   *          the template questionnaire id
   * @param videoId
   *          the video id where the questionnaire is
   * @param resource
   *          the resource
   * @return the created questionnaire
   * @throws ExtendedAnnotationException
   *           if an error occurs while storing/retrieving from persistence storage
   */
  Option<Questionnaire> createQuestionnaireFromTemplate(long templateQuestionnaireId,
          long videoId, Resource resource) throws ExtendedAnnotationException;

  /**
   * Get a questionnaire value by id.
   *
   * @param id
   *          the questionnaire value id
   * @param includeDeleted
   *          if <code>true</code> it will find also deleted questionnaires
   * @return the questionnaire
   * @throws ExtendedAnnotationException
   *           if an error occurs while storing/retrieving from persistence storage
   */
  Option<Questionnaire> getQuestionnaire(long id, boolean includeDeleted) throws ExtendedAnnotationException;

  /**
   * Get all questionnaires from a video.
   *
   * @param videoId the video id
   * @return the questionnaire list or an empty list if no questionnaires has been found
   * @throws ExtendedAnnotationException if an error occurs while storing/retrieving from persistence storage
   */
  Stream<Questionnaire> getQuestionnaires(long videoId) throws ExtendedAnnotationException;

  /**
   * Update a questionnaire.
   *
   * @param questionnaire
   *          the questionnaire to update
   * @throws ExtendedAnnotationException
   *           if an error occurs while storing/retrieving from persistence storage
   */
  void updateQuestionnaire(Questionnaire questionnaire) throws ExtendedAnnotationException;

  /**
   * Delete a questionnaire.
   *
   * @param questionnaire
   *          the questionnaire to delete
   * @return a representation of the deleted questionnaire
   * @throws ExtendedAnnotationException
   *           if an error occurs while storing/retrieving from persistence storage
   */
  Questionnaire deleteQuestionnaire(Questionnaire questionnaire) throws ExtendedAnnotationException;

  /**
   * Creates a template category
   *
   * @param seriesExtId
   *          the external series the category belongs to
   * @param seriesCategoryId
   *          the series category the category belongs to
   * @param videoId
   *          the video id
   * @param scaleId
   *          the scale that is used for this category
   * @param name
   *          the category name
   * @param description
   *          the category description
   * @param settings
   *          the category settings
   * @param resource
   *          the resource
   * @return the created category
   * @throws ExtendedAnnotationException
   *           if an error occurs while storing/retrieving from persistence storage
   */
  Category createCategory(Option<String> seriesExtId, Option<Long> seriesCategoryId, long videoId,
          Option<Long> scaleId, String name, Option<String> description, Option<String> settings, Resource resource)
          throws ExtendedAnnotationException;

  /**
   * Creates a category
   *
   * @param seriesExtId
   *          the external series the category belongs to
   * @param seriesCategoryId
   *          the series category the category belongs to
   * @param videoId
   *          the video id where the category is
   * @param resource
   *          the resource
   * @return the created category
   * @throws ExtendedAnnotationException
   *           if an error occurs while storing/retrieving from persistence storage
   */
  Option<Category> createCategoryFromTemplate(long templateCategoryId, String seriesExtId, Long seriesCategoryId,
          long videoId, Resource resource) throws ExtendedAnnotationException;

  /**
   * Get a category value by id.
   *
   * @param id
   *          the category value id
   * @param includeDeleted
   *          if <code>true</code> it will find also deleted categories
   * @return the category
   * @throws ExtendedAnnotationException
   *           if an error occurs while storing/retrieving from persistence storage
   */
  Option<Category> getCategory(long id, boolean includeDeleted) throws ExtendedAnnotationException;

  /**
   * Get all categories from a video.
   *
   * @param seriesExtId the external series the category belongs to
   * @param videoId     the video id
   * @return the category list or an empty list if no categories has been found
   * @throws ExtendedAnnotationException if an error occurs while storing/retrieving from persistence storage
   */
  Stream<Category> getCategories(Option<String> seriesExtId, long videoId) throws ExtendedAnnotationException;

  /**
   * Update a category.
   *
   * @param category
   *          the category to update
   * @throws ExtendedAnnotationException
   *           if an error occurs while storing/retrieving from persistence storage
   */
  void updateCategory(Category category) throws ExtendedAnnotationException;

  /**
   * Update a series category
   * Moves the master series category to the newVideoId and deletes all other series categories
   *
   * @param category
   *          the category to update
   * @throws ExtendedAnnotationException
   *          if an error occurs while storing/retrieving from persistence storage
   */
  void updateCategoryAndDeleteOtherSeriesCategories(Category category) throws ExtendedAnnotationException;

  /**
   * Delete a category.
   *
   * @param category
   *          the category to delete
   * @return a representation of the deleted category
   * @throws ExtendedAnnotationException
   *           if an error occurs while storing/retrieving from persistence storage
   */
  Category deleteCategory(Category category) throws ExtendedAnnotationException;

  /**
   * Creates a label
   *
   * @param categoryId
   *          the category id of this label
   * @param value
   *          the label value
   * @param abbreviation
   *          the label abbreviation
   * @param description
   *          the label description
   * @param settings
   *          the label settings
   * @param resource
   *          the resource
   * @return the created label
   * @throws ExtendedAnnotationException
   *           if an error occurs while storing/retrieving from persistence storage
   */
  Label createLabel(long categoryId, String value, String abbreviation, Option<String> description,
          Option<String> settings, Resource resource) throws ExtendedAnnotationException;

  /**
   * Get a label by id.
   *
   * @param id
   *          the label id
   * @param includeDeleted
   *          if <code>true</code> it will find also deleted labels
   * @return the label
   * @throws ExtendedAnnotationException
   *           if an error occurs while storing/retrieving from persistence storage
   */
  Option<Label> getLabel(long id, boolean includeDeleted) throws ExtendedAnnotationException;

  /**
   * Get all labels from a video.
   *
   * @param categoryId the category id
   * @return the label list or an empty list if no labels has been found
   * @throws ExtendedAnnotationException if an error occurs while storing/retrieving from persistence storage
   */
  Stream<Label> getLabels(long categoryId) throws ExtendedAnnotationException;

  /**
   * Update a label.
   *
   * @param label
   *          the label to update
   * @throws ExtendedAnnotationException
   *           if an error occurs while storing/retrieving from persistence storage
   */
  void updateLabel(Label label) throws ExtendedAnnotationException;

  /**
   * Delete a label.
   *
   * @param label
   *          the label to delete
   * @return a representation of the deleted label
   * @throws ExtendedAnnotationException
   *           if an error occurs while storing/retrieving from persistence storage
   */
  Label deleteLabel(Label label) throws ExtendedAnnotationException;

  /**
   * Creates a comment
   *
   * @param annotationId
   *          the annotation id
   * @param replyToId
   *          the id of the comment that the new comment is a reply to
   * @param text
   *          the comment text
   * @param resource
   *          the resource
   * @return the created comment
   */
  Comment createComment(long annotationId, Option<Long> replyToId, String text, Resource resource);

  /**
   * Get a comment by id.
   *
   * @param id
   *          the comment id
   * @return the comment
   */
  Option<Comment> getComment(long id);

  /**
   * Get all comments from an annotation
   *
   * @param annotationId the annotation id
   * @param replyToId    id of the comment to ge the replies to
   * @return the comment list or an empty list if no comments has been found
   */
  Stream<Comment> getComments(long annotationId, Option<Long> replyToId);

  /**
   * Update a comment
   *
   * @param comment
   *          the comment to update
   */
  void updateComment(Comment comment);

  /**
   * Delete a comment.
   *
   * @param comment
   *          the comment to delete
   * @return true if the comment existed and could be successfully deleted.
   */
  boolean deleteComment(Comment comment);

  /**
   * Create the base {@link Resource} for logging.
   *
   * @return the base resource
   */
  Resource createResource();

  /**
   * Create the base {@link Resource} for logging with tags.
   *
   * @param tags
   *          the tags map
   *
   * @return the base resource
   */
  Resource createResource(Option<Map<String, String>> tags);

  /**
   * Create the base {@link Resource} for logging with tags and access
   *
   * @param access
   *          the access level from the resource
   * @param tags
   *          the tags map
   * @return the base resource
   */
  Resource createResource(Option<Integer> access, Option<Map<String, String>> tags);

  /**
   * Update the resource update information with tags
   *
   * @param resource
   *          the base resource to update
   * @param tags
   *          the tags map
   * @return the updated base resource
   */
  Resource updateResource(Resource resource, Option<Map<String, String>> tags);

  /**
   * Update the resource deletion information
   *
   * @param resource
   *          the base resource to update
   * @return the updated base resource
   */
  Resource deleteResource(Resource resource);

  /**
   * Checks if the current user has access to the given resource
   *
   * @param resource
   *          the resource to check for access
   * @return true if the current user has access to the resource
   */
  boolean hasResourceAccess(Resource resource);

  /**
   * Checks whether the current user has a certain ACL action on a media package
   *
   * @param mediaPackage
   *          the media package to check for access
   * @param access
   *          a string representing the ACL action to check for
   * @return true if the user has the given ACL action on the given video
   */
  boolean hasVideoAccess(MediaPackage mediaPackage, String access);

  /** String representing the `annotate` ACL action */
  String ANNOTATE_ACTION = "annotate";
  /** String representing the `annotate-admin` ACL action */
  String ANNOTATE_ADMIN_ACTION = "annotate-admin";

  /**
   * Find the Opencast media package based on its id
   *
   * @param id
   *          the Opencast-level id of a media package
   * @return the media package corresponding to the given id, if it can be found
   */
  Option<MediaPackage> findMediaPackage(String id);
}
