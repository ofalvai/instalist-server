
package org.noorganization.instalist.server.api;

import com.fasterxml.jackson.databind.util.ISO8601Utils;
import org.noorganization.instalist.comm.message.Error;
import org.noorganization.instalist.comm.message.RecipeInfo;
import org.noorganization.instalist.comm.message.TagInfo;
import org.noorganization.instalist.server.CommonEntity;
import org.noorganization.instalist.server.TokenSecured;
import org.noorganization.instalist.server.controller.IRecipeController;
import org.noorganization.instalist.server.controller.impl.ControllerFactory;
import org.noorganization.instalist.server.model.DeletedObject;
import org.noorganization.instalist.server.model.DeviceGroup;
import org.noorganization.instalist.server.model.Recipe;
import org.noorganization.instalist.server.support.DatabaseHelper;
import org.noorganization.instalist.server.support.ResponseFactory;
import org.noorganization.instalist.server.support.exceptions.ConflictException;
import org.noorganization.instalist.server.support.exceptions.GoneException;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.text.ParseException;
import java.text.ParsePosition;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Path("/groups/{groupid}/tags")
public class TagResource {


    /**
     * Get a list of tags.
     * @param _groupId The id of the group containing the tags.
     * @param _changedSince Limits the request to elements that changed since the given date. ISO
     *                      8601 time e.g. 2016-01-19T11:54:07+0100. Optional.
     */
    @GET
    @TokenSecured
    @Produces({ "application/json" })
    public Response getTags(@PathParam("groupid") int _groupId,
                            @QueryParam("changedsince") String _changedSince) throws Exception {
        /*Instant changedSince = null;
        try {
            if (_changedSince != null)
                changedSince = ISO8601Utils.parse(_changedSince, new ParsePosition(0)).
                    toInstant();
        } catch (ParseException _e) {
            return ResponseFactory.generateBadRequest(CommonEntity.INVALID_DATE);
        }

        EntityManager manager = DatabaseHelper.getInstance().getManager();
        List<Recipe> recipes;
        List<DeletedObject> deletedRecipes;
        DeviceGroup group = manager.find(DeviceGroup.class, _groupId);

        if (changedSince != null) {
            TypedQuery<Recipe> recipeQuery = manager.createQuery("select r from Recipe r where " +
                    "r.group = :group and r.updated > :updated", Recipe.class);
            recipeQuery.setParameter("group", group);
            recipeQuery.setParameter("updated", changedSince);
            recipes = recipeQuery.getResultList();

            TypedQuery<DeletedObject> deletedRecipesQuery = manager.createQuery("select do " +
                    "from DeletedObject do where do.group = :group and do.time > :updated and " +
                    "do.type = :type", DeletedObject.class);
            deletedRecipesQuery.setParameter("group", group);
            deletedRecipesQuery.setParameter("updated", Date.from(changedSince));
            deletedRecipesQuery.setParameter("type", DeletedObject.Type.RECIPE);
            deletedRecipes = deletedRecipesQuery.getResultList();
        } else {
            recipes = new ArrayList<Recipe>(group.getRecipes());

            TypedQuery<DeletedObject> deletedRecipesQuery = manager.createQuery("select do " +
                    "from DeletedObject do where do.group = :group and do.type = :type",
                    DeletedObject.class);
            deletedRecipesQuery.setParameter("group", group);
            deletedRecipesQuery.setParameter("type", DeletedObject.Type.RECIPE);
            deletedRecipes = deletedRecipesQuery.getResultList();
        }
        manager.close();

        ArrayList<RecipeInfo> rtn = new ArrayList<RecipeInfo>(recipes.size() +
                deletedRecipes.size());
        for (Recipe current: recipes) {
            RecipeInfo toAdd = new RecipeInfo().withDeleted(false);
            toAdd.setUUID(current.getUUID());
            toAdd.setName(current.getName());
            toAdd.setLastChanged(Date.from(current.getUpdated()));
            rtn.add(toAdd);
        }
        for (DeletedObject current: deletedRecipes) {
            RecipeInfo toAdd = new RecipeInfo().withDeleted(true);
            toAdd.setUUID(current.getUUID());
            toAdd.setLastChanged(current.getTime());
            rtn.add(toAdd);
        }

        return ResponseFactory.generateOK(rtn);*/
        return null;
    }

    /**
     * Get a single tag.
     * @param _groupId The id of the group containing the tag.
     * @param _tagUUID The uuid of the requested tag.
     */
    @GET
    @TokenSecured
    @Path("{taguuid}")
    @Produces({ "application/json" })
    public Response getTag(@PathParam("groupid") int _groupId,
                           @PathParam("taguuid") String _tagUUID) throws Exception {
        /*UUID toFind;
        try {
            toFind = UUID.fromString(_recipeUUID);
        } catch (IllegalArgumentException _e) {
            return ResponseFactory.generateBadRequest(CommonEntity.INVALID_UUID);
        }

        EntityManager manager = DatabaseHelper.getInstance().getManager();
        IRecipeController recipeController = ControllerFactory.getRecipeController(manager);
        DeviceGroup group = manager.find(DeviceGroup.class, _groupId);

        Recipe current = recipeController.getRecipeByGroupAndUUID(group, toFind);
        if (current == null) {
            if (recipeController.getDeletedRecipeByGroupAndUUID(group, toFind) == null) {
                manager.close();
                return ResponseFactory.generateNotFound(new Error().withMessage("Recipe was not " +
                        "found."));
            }
            manager.close();
            return ResponseFactory.generateGone(new Error().withMessage("Recipe was deleted " +
                    "before."));
        }
        manager.close();

        RecipeInfo rtn = new RecipeInfo().withDeleted(false);
        rtn.setUUID(current.getUUID());
        rtn.setName(current.getName());
        rtn.setLastChanged(Date.from(current.getUpdated()));

        return ResponseFactory.generateOK(rtn);*/
        return null;
    }

    /**
     * Updates the tag.
     * @param _groupId The id of the group containing the tag to change.
     * @param _tagUUID The uuid of the tag identifying it in the group.
     * @param _entity Data to change.
     */
    @PUT
    @TokenSecured
    @Path("{taguuid}")
    @Consumes("application/json")
    @Produces({ "application/json" })
    public Response putTag(@PathParam("groupid") int _groupId,
                           @PathParam("taguuid") String _tagUUID,
                           TagInfo _entity) throws Exception {
        /*if ((_entity.getUUID() != null && !_entity.getUUID().equals(_tageUUID)) ||
                (_entity.getName() != null && _entity.getName().length() == 0) ||
                (_entity.getDeleted() != null && _entity.getDeleted()))
            return ResponseFactory.generateBadRequest(CommonEntity.sInvalidData);

        UUID toUpdate;
        try {
            toUpdate = UUID.fromString(_tageUUID);
        } catch (IllegalArgumentException _e) {
            return ResponseFactory.generateBadRequest(CommonEntity.INVALID_UUID);
        }
        Instant updated;
        if (_entity.getLastChanged() != null) {
            updated = _entity.getLastChanged().toInstant();
            if (Instant.now().isBefore(updated))
                return ResponseFactory.generateBadRequest(CommonEntity.INVALID_DATE);
        } else
            updated = Instant.now();

        EntityManager manager = DatabaseHelper.getInstance().getManager();
        IRecipeController recipeController = ControllerFactory.getRecipeController(manager);
        try {
            recipeController.update(_groupId, toUpdate, _entity.getName(), updated);
        } catch (NotFoundException _e) {
            return ResponseFactory.generateNotFound(new Error().withMessage("The recipe was not " +
                    "found."));
        } catch (GoneException _e) {
            return ResponseFactory.generateGone(new Error().withMessage("The recipe has been " +
                    "deleted."));
        } catch (ConflictException _e) {
            return ResponseFactory.generateConflict(new Error().withMessage("The sent data would " +
                    "conflict with saved recipe."));
        } finally {
            manager.close();
        }

        return ResponseFactory.generateOK(null);*/
        return null;
    }

    /**
     * Creates the tag.
     * @param _groupId The id of the group that should contain the new tag.
     * @param _entity Data needed for creation.
     */
    @POST
    @TokenSecured
    @Consumes("application/json")
    @Produces({ "application/json" })
    public Response postTag(@PathParam("groupid") int _groupId,
                            TagInfo _entity) throws Exception {
        /*try {


        if (_entity.getUUID() == null ||
                (_entity.getName() != null && _entity.getName().length() == 0) ||
                (_entity.getDeleted() != null && _entity.getDeleted()))
            return ResponseFactory.generateBadRequest(CommonEntity.sInvalidData);

        UUID toCreate;
        try {
            toCreate = UUID.fromString(_entity.getUUID());
        } catch (IllegalArgumentException _e) {
            return ResponseFactory.generateBadRequest(CommonEntity.INVALID_UUID);
        }
        Instant created;
        if (_entity.getLastChanged() != null) {
            created = _entity.getLastChanged().toInstant();
            if (Instant.now().isBefore(created))
                return ResponseFactory.generateBadRequest(CommonEntity.INVALID_DATE);
        } else
            created = Instant.now();

        EntityManager manager = DatabaseHelper.getInstance().getManager();
        IRecipeController recipeController = ControllerFactory.getRecipeController(manager);
        try {
            recipeController.add(_groupId, toCreate, _entity.getName(), created);
        } catch (ConflictException _e) {
            return ResponseFactory.generateConflict(new Error().withMessage("The sent data would " +
                    "conflict with saved recipe."));
        } finally {
            manager.close();
        }

        return ResponseFactory.generateCreated(null);
        }catch (Exception _e) {
            _e.printStackTrace();
            throw _e;
        }*/
        return null;
    }

    /**
     * Deletes the tag and linked tagged products (but not the products themselves).
     * @param _groupId The id of the group still containing the tag.
     * @param _tagUUID the uuid of the tag to delete.
     */
    @DELETE
    @TokenSecured
    @Path("{taguuid}")
    @Produces({ "application/json" })
    public Response deleteTag(@PathParam("groupid") int _groupId,
                              @PathParam("taguuid") String _tagUUID) throws Exception {
        /*UUID toDelete;
        try {
            toDelete = UUID.fromString(_tagUUID);
        } catch (IllegalArgumentException _e) {
            return ResponseFactory.generateBadRequest(CommonEntity.INVALID_UUID);
        }

        EntityManager manager = DatabaseHelper.getInstance().getManager();
        IRecipeController recipeController = ControllerFactory.getRecipeController(manager);
        try {
            recipeController.delete(_groupId, toDelete);
        } catch (NotFoundException _e) {
            return ResponseFactory.generateNotFound(new Error().withMessage("The recipe was not " +
                    "found."));
        } catch (GoneException _e) {
            return ResponseFactory.generateGone(new Error().withMessage("The recipe has been " +
                    "deleted."));
        } finally {
            manager.close();
        }

        return ResponseFactory.generateOK(null);*/
        return null;
    }

}
