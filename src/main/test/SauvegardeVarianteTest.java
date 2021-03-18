import org.example.model.Variante;
import org.example.model.VarianteManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

import java.io.File;

//Attention creation de fichier dans /AppData/Local/Temp
public class SauvegardeVarianteTest {
    public String filePath;
    public VarianteManager vm;

    @BeforeEach
    public void initializeTest() {
        filePath = System.getProperty("user.home") + "/AppData/Local/Temp/" + "Test.cbvr";
        vm = new VarianteManager();
    }

    @Test
    public void testEnregistrerVariante() {
        Variante vr = new Variante();
        vr.setName("TestVariante");

        vm.setCurrent(vr);
        vm.saveCurrent(filePath);

        Assertions.assertTrue(new File(filePath).isFile());
    }

    @Test
    public void testImporterVariante() {
        Variante vrSave = new Variante();
        vrSave.setName("TestVariante");

        vm.setCurrent(vrSave);
        vm.saveCurrent(filePath);

        Variante vr = vm.importFile(filePath);

        Variante vrToCompare = new Variante();
        vrToCompare.setName("TestVariante");

        Assertions.assertEquals(vr, vrToCompare);
    }
}
