package org.luncert.rjava;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletResponse;

import org.luncert.simpleutils.IOHelper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("javaClassExecutor")
public class JavaClassExecutor {

    @RequestMapping("execute")
    public String execute(@RequestParam("classFile") MultipartFile file, HttpServletResponse response) throws IOException {
        try {
            byte[] classByte = IOHelper.read(file.getInputStream());
            if (classByte.length > 0) {
                // execute
                HackSystem.clearBuffer();
                ClassModifier cm = new ClassModifier(classByte);
                byte[] modiBytes = cm.modifyUTF8Constant("java/lang/System", "org/luncert/rjava/HackSystem");
                HotSwapClassLoader loader = new HotSwapClassLoader();
                Class<?> clazz = loader.loadByte(modiBytes);

                try {
                    Method method = clazz.getMethod("main", String[].class);
                    method.invoke(null);
                } catch (Throwable e) {
                    e.printStackTrace(HackSystem.out);
                }

                return HackSystem.getBufferString();
            } else {
                return "please upload class file";
            }
        } catch (IOException e) {
            e.printStackTrace(response.getWriter());
            return null;
        }
    }

}