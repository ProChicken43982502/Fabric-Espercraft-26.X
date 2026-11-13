#version 330

#moj_import <minecraft:fog.glsl>
#moj_import <minecraft:dynamictransforms.glsl>

uniform sampler2D Sampler0;

in float sphericalVertexDistance;
in float cylindricalVertexDistance;
in vec4 vertexColor;
in vec4 lightMapColor;
in vec4 overlayColor;

flat in int filmStyle;

in vec2 texCoord0;

out vec4 fragColor;

vec3 applyFilm(vec3 rgb, int style) {
    float luma = dot(rgb, vec3(0.299, 0.587, 0.114));

    vec3 result = rgb;
    float saturation = 1.10;
    float contrast = 1.05;
    vec3 tint = vec3(1.0);

    if (style == 1) {
        saturation = 0.0;
        contrast = 1.0;
    }
    else if (style == 2) {
        saturation = 0.0;
        contrast = 1.0;
        tint = vec3(0.85, 0.70, 0.55);
    }
    else if (style == 3) {
        saturation = 1.10;
        contrast = 1.05;
        tint = vec3(0.55, 0.78, 1.0);
    }

    result = mix(vec3(luma), rgb, saturation);
    result = (result - 0.5) * contrast + 0.5;

    return clamp(result * tint, 0.0, 1.0);
}

void main() {
    vec4 color = texture(Sampler0, texCoord0);
    #ifdef ALPHA_CUTOUT
    if (color.a < ALPHA_CUTOUT) {
        discard;
    }
    #endif

    color *= vertexColor * ColorModulator;          // lighting and tint first
    color.rgb = applyFilm(color.rgb, filmStyle);    // then the filter, so tinted layers are filtered too
    color.rgb = mix(overlayColor.rgb, color.rgb, overlayColor.a);
    color *= lightMapColor;

    fragColor = apply_fog(color, sphericalVertexDistance, cylindricalVertexDistance, FogEnvironmentalStart, FogEnvironmentalEnd, FogRenderDistanceStart, FogRenderDistanceEnd, FogColor);
}