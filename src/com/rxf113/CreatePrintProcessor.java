package com.rxf113;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.JavaFileObject;
import java.io.*;
import java.util.Set;

/**
 * CreatePrintProcessor
 *
 * @author rxf113
 */
@SupportedAnnotationTypes(value = "com.rxf113.CreatePrint")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class CreatePrintProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (TypeElement typeElement : annotations) {
            for (Element element : roundEnv.getElementsAnnotatedWith(typeElement)) {
                //获取全限类名
                String fullClassName = element.toString();
                fullClassName = fullClassName.replace('.', '\\');

                //读取源文件
                File file = new File(fullClassName + ".java");
                try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                    String line;
                    //原代码
                    StringBuilder originCode = new StringBuilder();
                    while ((line = reader.readLine()) != null) {
                        originCode.append(line).append("\n");
                    }

                    //自定义一个print方法
                    String printFuncOne = "\tpublic void print() {\n" +
                            "        System.out.println(\" I am the method created by cusProcessor !!! \");\n" +
                            "        System.out.printf(\"age: %d   name: %s\", age, name);\n" +
                            "    }\n" +
                            "}";

                    //在原代码最后 添加方法
                    String newCode = originCode.toString().replaceAll("}", printFuncOne);

                    //创建一个和原来同名的类
                    JavaFileObject fileObject = processingEnv.getFiler().createSourceFile("com.rxf113.Person");
                    Writer w = fileObject.openWriter();
                    w.write(newCode);
                    w.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return true;
    }
}
