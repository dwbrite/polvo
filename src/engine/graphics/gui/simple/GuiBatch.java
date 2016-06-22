package engine.graphics.gui.simple;

import engine.graphics.Mesh;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.glDisableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL30.glBindVertexArray;

public class GuiBatch extends GuiObject {

    public GuiBatch(GuiObject... objects) {
        int numObjects = objects.length;
        Mesh[] meshes = new Mesh[numObjects];
        for (int i = 0; i < numObjects; i++) {
            meshes[i] = objects[i].getMesh();
        }
        this.mesh = Mesh.combine(meshes);
    }

    @Override
    public void render(double delta) {
        // Draw the mesh
        glBindVertexArray(mesh.getVaoId());
        glEnableVertexAttribArray(0);
        glEnableVertexAttribArray(1);
        glDrawElements(GL_TRIANGLES, mesh.getVertexCount(), GL_UNSIGNED_INT, 0);

        // Restore state
        glDisableVertexAttribArray(0);
        glDisableVertexAttribArray(1);
        glBindVertexArray(0);
    }
}
