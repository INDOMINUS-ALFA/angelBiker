package com.example.angelbiker.domain.DB;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;


import com.example.angelbiker.R;
import com.example.angelbiker.domain.DB.modelos.motos.Moto;
import com.example.angelbiker.domain.DB.modelos.motos.MotoDao;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// Add databese entity
@Database(entities = {Moto.class}, version = 1, exportSchema = false)
public abstract class MotosDB extends RoomDatabase {

    public abstract MotoDao mainDao();

    private static volatile MotosDB INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);


    private static MotosDB.Callback sRoomDatabaseCallback = new MotosDB.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            // If you want to keep data through app restarts,
            // comment out the following block
            databaseWriteExecutor.execute(() -> {
                // Populate the database in the background.
                // If you want to start with more words, just add them.
                MotoDao dao = INSTANCE.mainDao();
                dao.deleteAll();

                Moto moto = new Moto(999,
                        2019,
                        201,
                        80,
                        70,
                        90,
                        80,
                        80,
                        4 ,
                        115,
                        180,
                        20000,
                        "Aprilia RSV4 RR 2018",
                        "43 mm full ajustable",
                        "Aprilia V4 65°, 4-triempos, cuatro valvulas por cilindro.",
                        "Delantero: 330 mm doble disco perforado con pastilla Brembo M50\n "+ " Trasero Monodisco, pinza flotante de un émbolo, 220 mm de diámetro ",
                        "Control de tracción \n"  +
                                        " Control Anticaballito \n"  +
                                        "Launch-Control \n"  +
                                        " quick-Shifter \n"  +
                                        "velocidad_crucero \n" +
                                        "pit_limiter \n" +
                                        "ABS",
                        "Aclamada unánimemente por la crítica y codiciada por los motociclistas de todo el mundo, la RSV4 es el símbolo indiscutible del segmento y el referente en cuanto a deportividad y prestaciones. También es un brillante ejemplo de lo que la experiencia de un equipo de competición de éxito puede aportar, 54 campeonatos mundiales a sus espaldas en el transcurso de un espacio de tiempo relativamente corto. Las habilidades perfeccionadas en el circuito y transferidas a la línea de producción han permitido a los motoristas de todo el mundo experimentar las increíbles sensaciones y la emoción de Aprilia Racing.",
                        "SuperSport",
                        "Aprilia",
                        "A",
                        R.drawable.aprilia_rsv4_2019);

                dao.insert(moto);

                Moto moto2 = new Moto(999,
                        2015,
                        199,
                        84,
                        71,
                        67,
                        83,
                        75,
                        4 ,
                        113,
                        176,
                        19950,
                        "BMW S1000RR 2015",
                        "Horquilla invertida de 46 mm con ajuste en rebote y comprensión. Recorrido de 120 mm",
                        "4 cilindros 80 × 49.7 mm con inyeccion electroica",
                        "Delantero: Doble disco, pinzas flotantes, pinza fija de 4 pistones, 320 mm de diámetro.\n\n " + " Trasero Monodisco, pinza flotante de un émbolo, 220 mm de diámetro ",
                        "BMW Motorrad ABS \n\n ABS PRO\n\n DTC (Dynamic Traction Control)\n\n HSC (Hill Start Control)\n\n 4 modos de conducción (Rain, Road, Dynamic, Race)\n\n Asistente de cambio Pro\n\n Pantalla TFT, Intermitente LED\n\n Faro y luz rasera LED",
                        "Cualquiera que la vea se da cuenta inmediatamente: es sinónimo de máximas prestaciones. Cada pieza de la RR se ha diseñado para sacar el máximo de ella. La disposición del bastidor, incluida la nueva tecnología de chasis, hace que la RR sea más ligera y precisa. A esto se suma una posición de asiento ergonómicamente perfecta, que no tiene parangón debido a los cambios en el diseño del bastidor y del carenado. Los faros LED de serie le confieren un aspecto absolutamente dinámico y agresivo. Los intermitentes delanteros han pasado del carenado a los retrovisores, que no solo son estéticos, sino que también mejoran la visibilidad. En la parte trasera, la RR domina en términos de diseño y funcionalidad: \"Todo en uno\" es el lema. Además de la iluminación de la matrícula, el soporte de la matrícula ahora también incluye intermitentes LED, luz trasera y luz de freno.",
                        "Sport",
                        "BMW",
                        "A",
                        R.drawable.bmw_s1000rr_2015);
                dao.insert(moto2);

                Moto moto3 = new Moto(773,
                        2015,
                        48,
                        40,
                        46,
                        35,
                        65,
                        33,
                        2 ,
                        60,
                        196,
                        9399,
                        "Kawasaki W800 2015",
                        "Horquilla invertida de 46 mm con ajuste en rebote y comprensión. Recorrido de 120 mm",
                        "4 cilindros 80 × 49.7 mm con inyeccion electroica",
                        "Delantero: Doble disco, pinzas flotantes, pinza fija de 4 pistones, 320 mm de diámetro\n\n Trasero Monodisco, pinza flotante de un émbolo, 220 mm de diámetro ",
                        "BMW Motorrad ABS\n\n ABS PRO\n\n DTC (Dynamic Traction Control)\n\n HSC (Hill Start Control)\n\n 4 modos de conducción (Rain, Road, Dynamic, Race)\n\n Asistente de cambio Pro\n\n Pantalla TFT\n\n Intermitente LED\n\n Faro y luz rasera LED",
                        "La W800, al igual que las W800 STREET y W800 CAFE, es una motocicleta que cautiva a una amplia variedad de pilotos gracias a su maniobrabilidad ligera y a sus exquisitos acabados (kodawari). La W800 destaca especialmente de entre estos tres modelos vintage actualizados, postulándose como la más fiel a la W original, tanto a nivel estético como en cuanto a las sensaciones que ofrece al piloto, quien a su vez siente el placer y el orgullo de conducir una maravilla de los años 60. Con la llegada de la W800, los pilotos en busca de un sabor de historia, ahora pueden disfrutar de 3 sabores de W",
                        "Nacked",
                        "Kawasaki",
                        "A",
                        R.drawable.kawasaki_w800_2015);
                dao.insert(moto3);

                Moto moto4 = new Moto(1200,
                        2020,
                        300,
                        80,
                        100,
                        100,
                        100,
                        90,
                        4 ,
                        142,
                        238,
                        33850,
                        "Kawasaki Ninja H2",
                        "Horquilla telescópica invertida  de 43 mm con ajuste en Compresión, extensión y precarga . Recorrido de 120 mm",
                        "4 cilindros en linea con 4 valvulas por circuito con inyeccion electroicay turbocompresor",
                        "Delantero: Doble disco, Pinza monobloque de anclaje radial Brembo de 4 pistones , 330 mm de diámetro.\n\n Trasero Monodisco, Pinza de 2 pistones opuestos, 250 mm de diámetro ",
                        "Sistema de válvulas de aceleración electrónico\n\n  Motor sobrealimentado\n\n KLCM - Función del control de salida\n\n KQS - Cambio semiautomático de Kawasaki\n\n KCMF Función de asistencia en el giro \n\n Control del Freno de Motor Kawasaki\nV IMU\n\n Embrague anti-rebote\n\n Indicador ECO\n\n KTRC (3-modos, 9-niveles)\n\n Conectividad Smartphone\n\n ERGO-FIT\n\n Pintura Efecto-Espejo\n\n K-ACT ABS, ABS",
                        "Una moto aparte, la Kawasaki Ninja H2 2019 es la encarnación de la pasión de Kawasaki por las más altas prestaciones, por la búsqueda de la excelencia técnica y el deseo de alcanzar la perfección. Equipada con un compresor diseñado y construido enteramente en Kawasaki, la Ninja H2 hará las delicias de los amantes de las dos ruedas con un festival visual de detalles de alta calidad, además de crear un nuevo estándar de aceleración en motos homologadas para la calle",
                        "HyperSport",
                        "Kawasaki",
                        "A",
                        R.drawable.kawasaki_ninja_h2_2020);
                dao.insert(moto4);

                Moto moto5 = new Moto(998,
                        2020,
                        203,
                        71,
                        85,
                        62,
                        79,
                        68,
                        4 ,
                        114,
                        206,
                        17499,
                        "Kawasaki Ninja ZX10RR Replica 2020",
                        "Horquilla invertida de 43 mm con cámara de compresión externa y ajuste en compresión y extensión, capacidad de ajuste de precarga",
                        "4 cilindros en linea con 4 valvulas por circuito con inyeccion electroica",
                        "Delantero: Doble disco lobulados semiflotantes de 330 mm con pinza radial Brembo M50, 4 pistones opuestos\n\n Trasero: Disco lobulado de 220 mm. Pinza: pistón simple ",
                        " Sistema de válvulas de aceleración electrónico\n\n KLCM - Función del control de salida\n\n KQS - Cambio semiautomático de Kawasaki\n\n KCMF Función de asistencia en el giro \n\n Control del Freno de Motor Kawasaki\n\n IMU\n\n Indicador ECO\n\n KTRC\n\n KIBS(Kawasaki Intelligent anti-lock Brake System), ABS, Amortiguador trasero en disposición horizontal",
                        "El Kawasaki Racing Team y la fábrica de Japón forjaron una alianza para el desarrollo de la Ninja ZX-10R. Probada, refinada y aprobada por los campeones del mundo Jonathan Rea y Tom Sykes, es la más parecida a la que forma parte del equipo de Superbikes. Ninja ZX-10R Get Closer",
                        "SuperSport",
                        "Kawasaki",
                        "A",
                        R.drawable.kawasaki_zx10r_2020);
                dao.insert(moto5);

                Moto moto6 = new Moto(636,
                        2020,
                        130 ,
                        71,
                        64,
                        62,
                        70,
                        55,
                        4 ,
                        708,
                        206,
                        12550,
                        "Kawasaki Ninja ZX6R",
                        "Delantera: Horquilla invertida de 43 mm con cámara de compresión externa y ajuste en compresión y extensión, capacidad de ajuste de precarga.\n" + "Trasera: Horizontal Back-link con suspensión BFRC de gas con depósito externo. Ajuste en compresión. Ajuste en extensión. Precarga de muelle totalmente ajustable",
                        "4 cilindros en linea con 4 valvulas a 4 tiempos",
                        "Delantero: Doble disco lobulados semiflotantes de 330 mm con pinza radial Brembo M50, 4 pistones opuestos. Trasero: Disco lobulado de 220 mm. Pinza: pistón simple ",
                        " Sistema de válvulas de aceleración electrónico, KLCM - Función del control de salida, KQS - Cambio semiautomático de Kawasaki, KCMF Función de asistencia en el giro , Control del Freno de Motor Kawasaki, IMU, Indicador ECO, KTRC, KIBS(Kawasaki Intelligent anti-lock Brake System), ABS, Amortiguador trasero en disposición horizontal",
                        "El Kawasaki Racing Team y la fábrica de Japón forjaron una alianza para el desarrollo de la Ninja ZX-10R. Probada, refinada y aprobada por los campeones del mundo Jonathan Rea y Tom Sykes, es la más parecida a la que forma parte del equipo de Superbikes. Ninja ZX-10R Get Closer",
                        "SuperSport",
                        "Kawasaki",
                        "A",
                        R.drawable.kawasaki_zx6r_2020);
                dao.insert(moto6);
                Moto moto7 = new Moto(649,
                        2021,
                        68 ,
                        75,
                        65,
                        61,
                        75,
                        35,
                        2 ,
                        64,
                        193 ,
                        8350,
                        "Kawasaki Ninja 650",
                        "Delantera: Horquilla telescópica de 41 mm.\n\n" + "Trasera: Suspensión tipo Back-link con ajuste en precarga",
                        "Bicilindrico en paralelo a 4 tiempos",
                        "Delantero: Doble disco lobulados semiflotantes de 300 mm con pinza piston doble\n\n Trasero: Disco lobulado de 220 mm. Pinza: pistón simple ",
                        "KQS - Cambio semiautomático de Kawasaki \n\n  Embrague anti-rebote  \n\n KTRC (3-modos) \n\n Modos de potencia \n\n Indicador ECO\n\n KIBS Sistema de frenado antibloqueo inteligente Kawasaki \n\n  ABS",
                        "Chasis multitubular, motor bicilíndrico refrigerado por agua, un asiento bajo y una marcada personalidad Ninja. Una moto muy versátil con unas prestaciones imponentes que dejará satisfechos tanto a los más noveles, por su facilidad de conducción, como a los más expertos que exigen un poco más. ",
                        "SuperSport",
                        "Kawasaki",
                        "A2 limitada \n\n A deslimitada",
                        R.drawable.kawasaki_650_2021);
                dao.insert(moto7);
                Moto moto8 = new Moto(399,
                        2020,
                        45 ,
                        58,
                        36,
                        54,
                        61,
                        38,
                        2 ,
                        64,
                        193 ,
                        6599,
                        "Kawasaki Ninja 400 performance",
                        "Delantero  Disco lobulado de 310 mm. Pinza: Doble pistón \n\n Trasero  Disco lobulado de 220 mm. Pinza: pistón doble",
                        "4 tiempos, bicilíndrico en paralelo",
                        "Delantero Disco lobulado de 310 mm. Pinza: Doble pistón \n\n Trasero  Disco lobulado de 220 mm. Pinza: pistón doble ",
                        "Embrague anti-rebote\n\n  Indicador ECO\n\n  ERGO-FIT\n\n  ABS",
                        "La Ninja 400 ofrece unas prestaciones claramente mejoradas, en comparación con su predecesora a nivel de chasis, mayor potencia y ligereza. Esta Ninja posee mucho más que altas prestaciones. Ofrece acabados de alta calidad, un motor de grandes prestaciones ligera, fácil manejo y una posición relajada y deportiva que la hacen divertida y fácil de conducir",
                        "SuperSport",
                        "Kawasaki",
                        "A2 limitada \n\n A deslimitada",
                        R.drawable.kawasaki_400_2020);
                dao.insert(moto8);

                Moto moto9 = new Moto(998,
                        2020,
                        45 ,
                        40,
                        38,
                        56,
                        54,
                        39,
                        2 ,
                        64,
                        193 ,
                        4250,
                        "Kawasaki Ninja 125",
                        "Delantero  Disco lobulado de 310 mm. Pinza: Doble pistón \n\n Trasero  Disco lobulado de 220 mm. Pinza: pistón doble",
                        "4 tiempos, bicilíndrico en paralelo",
                        "Delantero Disco lobulado de 310 mm. Pinza: Doble pistón \n\n Trasero  Disco lobulado de 220 mm. Pinza: pistón doble ",
                        "Embrague anti-rebote\n\n  Indicador ECO\n\n  ERGO-FIT\n\n  ABS",
                        "Bienvenido a Kawasaki y al mundo Ninja. La nueva Ninja 125 apta para el A1 no es sólo el primer paso hacia la carrera Ninja, sino que presenta un carenado completo de la más alta calidad inspirado en la cuatro veces ganadora de WSBK, la Ninja ZX-10RR de Jonathan Rea. Con aspecto y tacto de moto grande, la nueva Ninja 125 encaja a la perfección en la familia Ninja. Con sus 11,0KW, se trata de un motor característico de Kawasaki que ofrece una gran potencia a altas revoluciones. La Ninja 125 es idónea para entrar en el mundo Kawasaki y empezar a disfrutar de las emociones del motociclismo",
                        "SuperSport",
                        "Kawasaki",
                        "B(coche) \n\n A1(ciclomotor)\n\n A2",
                        R.drawable.kawasaki_400_2020);
                dao.insert(moto9);

                Moto moto10 = new Moto(998,
                        2020,
                        203 ,
                        60,
                        78,
                        89,
                        64,
                        59,
                        4 ,
                        115,
                        206 ,
                        0,
                        "Kawasaki Ninja ZX10R MY 2021",
                        "Delantera: Horquilla invertida de 43 mm con cámara de compresión externa y ajuste en compresión y extensión, capacidad de ajuste de precarga. \n\n Trasera: Horizontal Back-link con suspensión BFRC de gas con depósito externo. Ajuste en compresión. Ajuste en extensión. Precarga de muelle totalmente ajustable ",
                        "Refrigeración líquida, 4 tiempos, cuatro cilindro en línea",
                        "Delantero Discos lobulados dobles semiflotantes de 330 mm. Pinza: montaje radial monobloc, Brembo M50, 4 pistones opuestos. \n\n Trasero:  Disco lobulado de 220 mm. Pinza: pistón simple ",
                        "Control del Freno de Motor Kawasaki\n\n  KLCM - Función del control de salida\n\n  IMU\n\n  KQS - Cambio semiautomático de Kawasaki\n\n Sistema de válvulas de aceleración electrónico \n\n KCMF Función de asistencia en el giro  \n\n S-KTRC \n\n Modos de potencia\n\n Control de crucero \n\n Indicador ECO \n\n Conectividad Smartphone\n\n KIBS Sistema de frenado antibloqueo inteligente Kawasaki\n\n ABS\n\n Amortiguador trasero en disposición horizontal " ,
                        "Siguiendo los parámetros de desarrollo del Kawasaki Racing Team, Kawasaki ha trabajado a fondo en el corazón de la Ninja ZX-10R para conseguir un nuevo modelo que nos permita seguir liderando con mano firme el campeonato del Mundo de SBK. Ninja ZX-10R Face Yourself. ",
                        "SuperSport",
                        "Kawasaki",
                        "A",
                        R.drawable.kawasaki_zx10r_2021);
                dao.insert(moto10);

                Moto moto11 = new Moto(998,
                        2021,
                        204 ,
                        65,
                        74,
                        70,
                        78,
                        59,
                        4 ,
                        112,
                        206 ,
                        0,
                        "Kawasaki Ninja ZX10RR 2021",
                        "Delantera: Horquilla invertida de 43 mm con cámara de compresión externa y ajuste en compresión y extensión, capacidad de ajuste de precarga. \n\n Trasera: Horizontal Back-link con suspensión BFRC de gas con depósito externo. Ajuste en compresión. Ajuste en extensión. Precarga de muelle totalmente ajustable ",
                        "Refrigeración líquida, 4 tiempos, cuatro cilindro en línea",
                        "Delantero Discos lobulados dobles semiflotantes de 330 mm. Pinza: montaje radial monobloc, Brembo M50, 4 pistones opuestos. \n\n Trasero:  Disco lobulado de 220 mm. Pinza: pistón simple ",
                        "Control del Freno de Motor Kawasaki\n\n  KLCM - Función del control de salida\n\n  IMU\n\n  KQS - Cambio semiautomático de Kawasaki\n\n Sistema de válvulas de aceleración electrónico \n\n KCMF Función de asistencia en el giro  \n\n S-KTRC \n\n Modos de potencia\n\n Control de crucero \n\n Indicador ECO \n\n Conectividad Smartphone\n\n KIBS Sistema de frenado antibloqueo inteligente Kawasaki\n\n ABS\n\n Amortiguador trasero en disposición horizontal " ,
                        "Siguiendo los parámetros de desarrollo del Kawasaki Racing Team, Kawasaki ha trabajado a fondo en el corazón de la Ninja ZX-10R para conseguir un nuevo modelo que nos permita seguir liderando con mano firme el campeonato del Mundo de SBK. Ninja ZX-10R Face Yourself. ",
                        "SuperSport",
                        "Kawasaki",
                        "A",
                        R.drawable.kawasaki_zx10rr_2021);
                dao.insert(moto11);

                Moto moto12 = new Moto(998,
                        2021,
                        200 ,
                        60,
                        62,
                        51,
                        89,
                        85,
                        4 ,
                        137,
                        235,
                        0,
                        "Kawasaki Z H2 SE",
                        "Delantera:     Horquilla invertida de 41 mm con ajuste en extensión y ajuste de precarga . \n\n Trasera: Horizontal Back-link, de gas, con ajuste en extensión y ajuste de precarga remota",
                        "Refrigeración líquida, 4 tiempos, cuatro cilindro en línea",
                        "Delantero:    Discos lobulados dobles semiflotantes de 300 mm. Pinza: montaje radial doble, monobloque, 4 pistones opuestos . \n\n Trasero:  Disco lobulado de 250 mm. Calibrador: pistón único",
                        "Motor sobrealimentado\n\n  KLCM - Función del control de salida\n\n  IMU\n\n  KQS - Cambio semiautomático de Kawasaki\n\n Sistema de válvulas de aceleración electrónico \n\n KCMF Función de asistencia en el giro  \n\n S-KTRC \n\n Modos de potencia\n\n Control de crucero \n\n Indicador ECO \n\n Conectividad Smartphone\n\n KIBS Sistema de frenado antibloqueo inteligente Kawasaki\n\n ABS\n\n Amortiguador trasero en disposición horizontal " ,
                        "El siguiente nivel en las Supernaked ha llegado. La nueva ZH2 SE está potenciada por la tecnologia exclusiva del Supercharger de Kawasaki, con nuevas características que  ofrecen un rendimiento sin igual: nueva suspensión electrónica Showa con la\n" +
                                "tecnologia Shyhook, los frenos Brembo de la H2, nuevos colores, se han juntado para potenciar tus emociones más allá de los límites SUPERCHARGE!  ",
                        "SuperNaked",
                        "Kawasaki",
                        "A",
                        R.drawable.kawasaki_zx10rr_2021);
                dao.insert(moto12);
            });
        }
    };


    // Define database name

    public static String DATABASE_NAME = "BaseDeDatos";

    public static MotosDB getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (MotosDB.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            MotosDB.class, DATABASE_NAME).allowMainThreadQueries()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }




}
